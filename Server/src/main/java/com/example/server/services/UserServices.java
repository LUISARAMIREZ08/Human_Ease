package com.example.server.services;

import com.example.server.controller.request.UserUpdateRequestDTO;
import com.example.server.entity.UserEntity;
import com.example.server.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    IUserRepository userRepository;
    // This method returns all the persons in the database
    public ArrayList<UserEntity> getPersons(){
        return (ArrayList<UserEntity>) userRepository.findAll();
    }
    // This method returns a person by id
    public Optional<UserEntity> getPersonById(Long id){
        return userRepository.findById(id);
    }

    // This method updates a person by id
    public UserEntity updatePersonById(UserUpdateRequestDTO request, Long id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found with id " + id));
        userEntity.setUsername(request.getUsername());
        userEntity.setName(request.getName());
        userEntity.setLastName(request.getLastName());
        userEntity.setBirthDate(LocalDate.parse(request.getBirthDate()));
        userEntity.setAddress(request.getAddress());
        userEntity.setPhone(request.getPhone());
        userEntity.setEmail(request.getEmail());
        userEntity.setRoleUser(request.getRoleUser());
        return userRepository.save(userEntity);
    }
    // This method deletes a person by id
    public Boolean deletePerson(Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User Not Found with username: " + username));
    }
}
