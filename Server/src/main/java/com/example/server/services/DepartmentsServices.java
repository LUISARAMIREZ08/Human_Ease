package com.example.server.services;

import com.example.server.repository.IDepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentsServices {
    @Autowired
    IDepartmentsRepository departmentsRepository;
}
