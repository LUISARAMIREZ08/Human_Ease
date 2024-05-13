package com.example.server.services;

import com.example.server.repository.IReferencesPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferencesServices {
    @Autowired
    IReferencesPersonRepository referencesPersonRepository;
}
