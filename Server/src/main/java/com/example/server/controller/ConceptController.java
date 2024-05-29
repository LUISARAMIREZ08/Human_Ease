package com.example.server.controller;

import com.example.server.entity.Concept;
import com.example.server.services.ConceptServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concept")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ConceptController {

    @Autowired
    private ConceptServices conceptServices;

    @GetMapping
    public List<Concept> getConcept(){
        return conceptServices.getConcept();
    }

    @GetMapping(value = "/{id}")
    public Concept getConceptById(@PathVariable Long id){
        return conceptServices.getConceptById(id);
    }

    @PostMapping
    public Concept saveConcept(@RequestBody Concept request){
        return conceptServices.saveConcept(request);
    }
}
