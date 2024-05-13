package com.example.server.services;

import com.example.server.repository.IPsychotechnicalTestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PsychotechnicalServices {
    @Autowired
    IPsychotechnicalTestsRepository psychotechnicalTestsRepository;
}
