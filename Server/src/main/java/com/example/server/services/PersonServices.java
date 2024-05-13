package com.example.server.services;

import com.example.server.entity.Person;
import com.example.server.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonServices {
    @Autowired
    IPersonRepository personRepository;
    // This method returns all the persons in the database
    public ArrayList<Person> getPersons(){
        return (ArrayList<Person>) personRepository.findAll();
    }
    // This method returns a person by id
    public Optional<Person> getPersonById(Long id){
        return personRepository.findById(id);
    }
    // This method saves a person to the database
    public Person savePerson(Person person){
        return personRepository.save(person);
    }
    // This method updates a person by id
    public Person updatePersonById(Person request, Long id){
        Person person = personRepository.findById(id).get();
        person.setCardId(request.getCardId());
        person.setUserName(request.getUserName());
        person.setName(request.getName());
        person.setLastName(request.getLastName());
        person.setBirthDate(request.getBirthDate());
        person.setAddress(request.getAddress());
        person.setPhone(request.getPhone());
        person.setEmail(request.getEmail());
        person.setPassword(request.getPassword());
        return personRepository.save(person);
    }
    // This method deletes a person by id
    public Boolean deletePerson(Long id){
        try{
            personRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
