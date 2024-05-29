package com.example.server.services;

import com.example.server.entity.Departments;
import com.example.server.repository.IDepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentsServices {
    @Autowired
    IDepartmentsRepository departmentsRepository;

    //method to return all departments
    public List<Departments> getAllDepartments() {
        return departmentsRepository.findAll();
    }

    //method to return department by id
    public Optional<Departments> getDepartmentById(Long id) {
        return departmentsRepository.findById(id);
    }

    //method to save a department to the database
    public Departments saveDepartment(Departments request) {
        return departmentsRepository.save(request);
    }
}
