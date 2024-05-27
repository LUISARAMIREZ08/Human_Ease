package com.example.server.services;

import com.example.server.config.jwt.JwtUtils;
import com.example.server.controller.request.AuthResponseDTO;
import com.example.server.controller.request.LoginRequestDTO;
import com.example.server.controller.request.RegisterRequestDTO;
import com.example.server.entity.UserEntity;
import com.example.server.entity.enums.RoleUser;
import com.example.server.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtUtils.getToken(user);
        return AuthResponseDTO.builder()
                .token(token)
                .build();
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.existsByCardId(request.getCardId())) {
            throw new RuntimeException("Card id already exists");
        }
        UserEntity userEntity = UserEntity.builder()
                .cardId(request.getCardId())
                .username(request.getUsername())
                .name(request.getName())
                .lastName(request.getLastName())
                .birthDate(LocalDate.parse(request.getBirthDate()))
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(passwordEncoder.encode( request.getPassword()))
                .roleUser(RoleUser.CANDIDATE)
                .build();

        userRepository.save(userEntity);

        return AuthResponseDTO.builder()
                .token(jwtUtils.getToken(userEntity))
                .build();
    }

}
