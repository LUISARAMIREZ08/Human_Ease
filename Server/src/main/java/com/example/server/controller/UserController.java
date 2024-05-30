package com.example.server.controller;

import com.example.server.entity.UserEntity;
import com.example.server.repository.IUserRepository;
import com.example.server.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping
    public List<UserEntity> getUsers(){
        return userServices.getPersons();
    }

    @GetMapping(value ="/{id}")
    public UserEntity getUserById(@PathVariable Long id){
        return userServices.getPersonById(id).get();
    }

}
