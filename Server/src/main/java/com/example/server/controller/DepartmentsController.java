package com.example.server.controller;

import com.example.server.entity.Departments;
import com.example.server.services.DepartmentsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentsController {

    @Autowired
    private DepartmentsServices departmentsServices;

    @GetMapping
    public List<Departments> getAllDepartments() {
        return this.departmentsServices.getAllDepartments();
    }

    @GetMapping(path = "/{id}")
    public Optional<Departments> getDepartmentById(@PathVariable("id") Long id) {
        return this.departmentsServices.getDepartmentById(id);
    }

    @PostMapping
    public Departments saveDepartment(@RequestBody Departments request) {
        return this.departmentsServices.saveDepartment(request);
    }

}
