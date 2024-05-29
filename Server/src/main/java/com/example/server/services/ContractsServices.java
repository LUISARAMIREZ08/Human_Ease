package com.example.server.services;

import com.example.server.controller.response.ContractsResponseDTO;
import com.example.server.controller.request.ContratcsRequestDTO;
import com.example.server.entity.Contratcs;
import com.example.server.entity.Employee;
import com.example.server.entity.enums.ContractType;
import com.example.server.repository.IContratcsRepository;
import com.example.server.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContractsServices {
    @Autowired
    IContratcsRepository contractsRepository;

    @Autowired
    IEmployeeRepository employeeRepository;

    //This method return all contracts
    public List<Contratcs> getAllContracts() {
        return contractsRepository.findAll();
    }
    //This method return contract by id
    public Optional<Contratcs> getContractById(Long id) {
        return contractsRepository.findById(id);
    }
    //This method save a contract to the database
    public ContractsResponseDTO saveContract(ContratcsRequestDTO request, Contratcs contract) {
        contract.setContractStartDate(LocalDate.parse(request.getContractStartDate()));
        contract.setContractEndDate(LocalDate.parse(request.getContractEndDate()));
        contract.setContractPath(request.getContractPath());
        contract.setContractType(ContractType.valueOf(request.getContractType()));
        contract.setEmployee(findEmployee(request.getEmployeeId()));
        Contratcs savedContract = contractsRepository.save(contract);

        return convertToDTO(savedContract);
    }

    private Employee findEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() ->
                new RuntimeException("Position not found with id " + employeeId));
    }

    private ContractsResponseDTO convertToDTO(Contratcs contract) {
        ContractsResponseDTO dto = new ContractsResponseDTO();
        dto.setContractId(contract.getContractId());
        dto.setContractStartDate(String.valueOf(contract.getContractStartDate()));
        dto.setContractEndDate(String.valueOf(contract.getContractEndDate()));
        dto.setContractPath(contract.getContractPath());
        dto.setContractType(contract.getContractType().name());
        dto.setEmployeeId(contract.getEmployee().getEmployeeId());
        return dto;
    }
}
