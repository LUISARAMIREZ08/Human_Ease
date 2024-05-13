package com.example.server.services;

import com.example.server.repository.ICandidateApplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateApplicationsServices {
    @Autowired
    ICandidateApplicationsRepository candidateApplicationsRepository;
}
