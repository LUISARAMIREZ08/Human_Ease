package com.example.server.services;

import com.example.server.controller.request.AffiliationsRequestDTO;
import com.example.server.entity.Affiliations;
import com.example.server.entity.enums.StatusEmployee;
import com.example.server.repository.IAffiliationsRepository;
import com.example.server.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AffiliationsServices {
    @Autowired
    IAffiliationsRepository affiliationsRepository;

    @Autowired
    IEmployeeRepository employeeRepository;

    //This method return all the affiliations
    public List<AffiliationsRequestDTO> getAllAffiliations() {
        return affiliationsRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private AffiliationsRequestDTO toDTO(Affiliations affiliations) {
        AffiliationsRequestDTO dto = new AffiliationsRequestDTO();
        dto.setAffiliationId(affiliations.getAffiliationId());
        dto.setEntityCode(affiliations.getEntityCode());
        dto.setEntityType(affiliations.getEntityType());
        dto.setEntityName(affiliations.getEntityName());
        dto.setAffiliationDate(affiliations.getAffiliationDate().toString());
        dto.setAffiliationStatus(affiliations.getAffiliationStatus().name());
        dto.setEmployeeId(affiliations.getEmployee().getEmployeeId());
        return dto;
    }

    //This method return affiliation by id
    public AffiliationsRequestDTO getAffiliationById(Long id){
        Optional<Affiliations> affiliations = affiliationsRepository.findById(id);
        if (affiliations.isPresent()) {
            return toDTO(affiliations.get());
        }
        return null;
    }

    //This method save a new affiliation
    public AffiliationsRequestDTO saveAffiliation(AffiliationsRequestDTO dto, Affiliations affiliations){
        affiliations.setEntityCode(dto.getEntityCode());
        affiliations.setEntityType(dto.getEntityType());
        affiliations.setEntityName(dto.getEntityName());
        affiliations.setAffiliationDate(LocalDate.parse(dto.getAffiliationDate()));
        affiliations.setAffiliationStatus(StatusEmployee.valueOf(dto.getAffiliationStatus()));
        affiliations.setEmployee(employeeRepository.findById(dto.getEmployeeId()).get());
        Affiliations savedAffiliations = affiliationsRepository.save(affiliations);
        return toDTO(savedAffiliations);
    }
}
