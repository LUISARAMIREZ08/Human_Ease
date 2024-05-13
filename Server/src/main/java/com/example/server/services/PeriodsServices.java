package com.example.server.services;

import com.example.server.repository.IPeriodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodsServices {
    @Autowired
    IPeriodsRepository periodsRepository;
}
