package com.example.server.services;

import com.example.server.entity.UserEntity;
import com.example.server.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    /*public UserEntity updatePersonById(UserEntity request, Long id){
        UserEntity userEntity = personRepository.findById(id).get();
        userEntity.setUsername(request.getUsername());
        userEntity.setName(request.getName());
        userEntity.setLastName(request.getLastName());
        userEntity.setBirthDate(request.getBirthDate());
        userEntity.setAddress(request.getAddress());
        userEntity.setPhone(request.getPhone());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setRoleUser(request.getRoleUser());
        return personRepository.save(userEntity);
    }*/
    // This method deletes a person by id
    public Boolean deletePerson(Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
