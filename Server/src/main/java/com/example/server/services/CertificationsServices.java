package com.example.server.services;

import com.example.server.controller.request.CertificationsRequestDTO;
import com.example.server.controller.response.CertificationsResponseDTO;
import com.example.server.entity.Certifications;
import com.example.server.entity.Contratcs;
import com.example.server.entity.Employee;
import com.example.server.repository.ICertificationsRepository;
import com.example.server.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CertificationsServices {
    @Autowired
    ICertificationsRepository certificationsRepository;

    @Autowired
    IEmployeeRepository employeeRepository;

    //This method return all certifications
    public List<CertificationsResponseDTO> getAllCertifications() {
        return certificationsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    //This method return certifications by id
    public CertificationsResponseDTO getCertificationById(Long id){
        Optional<Certifications> certification = certificationsRepository.findById(id);
        if (certification.isPresent()) {
            return convertToDTO(certification.get());
        } else {
            throw new RuntimeException("Certification not found with id " + id);
        }
    }

    //This method save a certifications to the database
    public CertificationsResponseDTO saveCertification(CertificationsRequestDTO request, Certifications certification){
        certification.setCertificationName(request.getCertificationName());
        certification.setCertificationEntity(request.getCertificationEntity());
        certification.setCertificationDateOfIssue(LocalDate.parse(request.getCertificationDateOfIssue()));
        certification.setEmployee(findEmployee(request.getEmployeeId()));

        Certifications savedCertification = certificationsRepository.save(certification);

        return convertToDTO(savedCertification);
    }

    private Employee findEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() ->
                new RuntimeException("Position not found with id " + employeeId));
    }

    private CertificationsResponseDTO convertToDTO(Certifications savedCertification) {
        CertificationsResponseDTO dto = new CertificationsResponseDTO();
        dto.setCertificationId(savedCertification.getCertificationId());
        dto.setCertificationName(savedCertification.getCertificationName());
        dto.setCertificationEntity(savedCertification.getCertificationEntity());
        dto.setCertificationDateOfIssue(String.valueOf(savedCertification.getCertificationDateOfIssue()));
        dto.setEmployeeId(savedCertification.getEmployee().getEmployeeId());
        return dto;
    }
}
