package com.example.server.services;

import com.example.server.repository.IRequirementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequirementsServices {
    @Autowired
    IRequirementsRepository requirementsRepository;
}
