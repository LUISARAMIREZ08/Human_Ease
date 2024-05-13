package com.example.server.services;

import com.example.server.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmploeeServices {
    @Autowired
    IEmployeeRepository employeeRepository;
}
