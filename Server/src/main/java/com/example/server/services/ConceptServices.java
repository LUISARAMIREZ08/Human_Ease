package com.example.server.services;

import com.example.server.repository.IConceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConceptServices {
    @Autowired
    IConceptRepository conceptRepository;
}
