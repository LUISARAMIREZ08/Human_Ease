package com.example.server.services;

import com.example.server.repository.IRecordPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordPersonServices {
    @Autowired
    IRecordPersonRepository recordPersonRepository;
}
