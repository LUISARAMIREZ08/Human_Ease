package com.example.server.services;

import com.example.server.controller.request.EmployeeRequestDTO;
import com.example.server.controller.response.employee.EmployeeJoin;
import com.example.server.controller.response.employee.EmployeeJoinCostCenter;
import com.example.server.controller.response.employee.EmployeeResponseDTO;
import com.example.server.entity.CostCenter;
import com.example.server.entity.Employee;
import com.example.server.entity.Position;
import com.example.server.entity.UserEntity;
import com.example.server.entity.enums.StatusEmployee;
import com.example.server.repository.ICostCenterRepository;
import com.example.server.repository.IEmployeeRepository;
import com.example.server.repository.IPositionRepository;
import com.example.server.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServices {
    @Autowired
    IEmployeeRepository employeeRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IPositionRepository positionRepository;

    @Autowired
    ICostCenterRepository costCenterRepository;

    //This method return all empolyee
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    //Converts the entity to a DTO
    public EmployeeResponseDTO toDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setCardId(employee.getUserEntity().getCardId());
        dto.setAccountNumberEmployee(employee.getAccountNumberEmployee());
        dto.setSalaryBase(employee.getSalaryBase());
        dto.setStatusEmployee(employee.getStatusEmployee().name());
        dto.setPositionId(employee.getPosition().getPositionId());
        dto.setCostCenterId(employee.getCostCenter().getCostCenterId());

        return dto;
    }

    //This method return empolyee by id
    public EmployeeResponseDTO getEmployeeById(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return toDTO(employee.get());
        } else {
            return null;
        }
    }

    //This method save a empolyee to the database
    public Employee saveEmployee(EmployeeRequestDTO request) {
        Employee employee = new Employee();
        mapRequestToEmployee(request, employee);
        return employeeRepository.save(employee);
    }

    private void mapRequestToEmployee(EmployeeRequestDTO request, Employee employee) {
        employee.setEmployeeId(request.getEmployeeId());
        employee.setAccountNumberEmployee(request.getAccountNumberEmployee());
        employee.setSalaryBase(request.getSalaryBase());
        employee.setStatusEmployee(StatusEmployee.valueOf(request.getStatusEmployee()));
        employee.setUserEntity(findUserEntity(request.getCardId()));
        employee.setPosition(findPosition(request.getPositionId()));
        employee.setCostCenter(findCostCenter(request.getCostCenterId()));
    }

    private UserEntity findUserEntity(Long cardId) {
        return userRepository.findById(cardId).orElseThrow(() ->
                new RuntimeException("Person not found with id " + cardId));
    }

    private Position findPosition(Long positionId) {
        return positionRepository.findById(positionId).orElseThrow(() ->
                new RuntimeException("Position not found with id " + positionId));
    }

    private CostCenter findCostCenter(Long costCenterId) {
        return costCenterRepository.findById(costCenterId).orElseThrow(() ->
                new RuntimeException("Cost Center not found with id " + costCenterId));
    }

    //This method return employed join position and user
    public List<EmployeeJoin> getEmployeeAndUserAndPosition() {
        return employeeRepository.getEmployeeAndUserAndPosition();
    }

    //This method return employed join cost center and user
    public List<EmployeeJoinCostCenter> getEmployeeAndUserAndCostCenter() {
        return employeeRepository.getEmployeeAndUserAndCostCenter();
    }
}
