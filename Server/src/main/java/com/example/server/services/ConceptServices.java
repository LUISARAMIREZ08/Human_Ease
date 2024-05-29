package com.example.server.services;

import com.example.server.entity.Concept;
import com.example.server.entity.enums.ConceptType;
import com.example.server.repository.IConceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ConceptServices {
    @Autowired
    IConceptRepository conceptRepository;

    //This method return all concept
    public ArrayList<Concept> getConcept(){
        return (ArrayList<Concept>) conceptRepository.findAll();
    }

    //This method return concept by id
    public Concept getConceptById(Long id){
        return conceptRepository.findById(id).orElse(null);
    }

    //This method save a concept to the database
    public Concept saveConcept(Concept concept){
        return conceptRepository.save(concept);
    }
}
