package com.example.server.controller;

import com.example.server.entity.Person;
import com.example.server.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServices personServices;

    @GetMapping
    public List<Person> getPersons(){
        return this.personServices.getPersons();
    }

    @GetMapping(path = "/{id}")
    public Optional<Person> getPersonById(@PathVariable("id") Long id){
        return this.personServices.getPersonById(id);
    }
    @PostMapping
    public Person savePerson(@RequestBody Person person){
        return this.personServices.savePerson(person);
    }

    @PutMapping(path = "/{id}")
    public Person updatePersonById(@RequestBody Person request, @PathVariable Long id){
        return this.personServices.updatePersonById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.personServices.deletePerson(id);
        if(ok){
            return "Person with id: " + id + " deleted";
        }else{
            return "Person with id: " + id + " not deleted";
        }
    }
}
