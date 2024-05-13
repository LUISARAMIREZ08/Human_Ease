package com.example.server.services;

import com.example.server.repository.IContratcsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractsServices {
    @Autowired
    IContratcsRepository contractsRepository;
}
