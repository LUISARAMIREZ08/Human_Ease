package com.example.server.controller;

import com.example.server.controller.request.EmployeeRequestDTO;
import com.example.server.controller.response.employee.EmployeeAdmin;
import com.example.server.controller.response.employee.EmployeeJoin;
import com.example.server.controller.response.employee.EmployeeJoinCostCenter;
import com.example.server.controller.response.employee.EmployeeResponseDTO;
import com.example.server.entity.Employee;
import com.example.server.services.EmployeeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {
        return this.employeeServices.getAllEmployees();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeResponseDTO empleadoDTO = employeeServices.getEmployeeById(id);
        if (empleadoDTO != null) {
            return new ResponseEntity<>(empleadoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody EmployeeRequestDTO request) {
        return this.employeeServices.saveEmployee(request);
    }

    //Trabajadores
    @GetMapping(path = "/all")
    public ResponseEntity<List<EmployeeJoin>> getAllEmployeesAndUsersAndPositions() {
        List<EmployeeJoin> employees = employeeServices.getEmployeeAndUserAndPosition();
        return ResponseEntity.ok(employees);
    }

    //Nomina
    @GetMapping(path = "/allCostCenter")
    public ResponseEntity<List<EmployeeJoinCostCenter>> getAllEmployeesAndUsersAndCostCenter() {
        List<EmployeeJoinCostCenter> employees = employeeServices.getEmployeeAndUserAndCostCenter();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<EmployeeAdmin>> getAdminEmployees() {
        List<EmployeeAdmin> admins = employeeServices.getAdminEmployees();
        return ResponseEntity.ok(admins);
    }
}
