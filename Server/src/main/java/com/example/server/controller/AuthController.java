package com.example.server.controller;

import com.example.server.controller.request.AuthResponseDTO;
import com.example.server.controller.request.LoginRequestDTO;
import com.example.server.controller.request.RegisterRequestDTO;
import com.example.server.controller.request.UserUpdateRequestDTO;
import com.example.server.entity.UserEntity;
import com.example.server.services.AuthService;
import com.example.server.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserServices userServices;

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping(value = "current-user")
    public UserEntity obtenerUsuarioActual(Principal principal)
    {
        return (UserEntity) this.userServices.loadUserByUsername(principal.getName());
    }

    // This method updates a person by id
    @PutMapping(path = "/update-user/{id}")
    public UserEntity updatePersonById(@RequestBody UserUpdateRequestDTO request, @PathVariable Long id) {
        return this.userServices.updatePersonById(request, id);
    }
}
