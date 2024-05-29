package com.example.server.services;

import com.example.server.controller.request.JobOfferRequestDTO;
import com.example.server.controller.response.JobOfferResponseDTO;
import com.example.server.entity.Employee;
import com.example.server.entity.JobOffer;
import com.example.server.entity.Position;
import com.example.server.entity.enums.JobOfferStatus;
import com.example.server.repository.IEmployeeRepository;
import com.example.server.repository.IJobOfferRepository;
import com.example.server.repository.IPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobOfferServices {
    @Autowired
    IJobOfferRepository jobOfferRepository;

    @Autowired
    IEmployeeRepository employeeRepository;

    @Autowired
    IPositionRepository positionRepository;

    //This method return all job offers
    public List<JobOfferResponseDTO> getAllJobOffers() {
        return jobOfferRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private JobOfferResponseDTO convertToDTO(JobOffer jobOffer) {
        JobOfferResponseDTO dto = new JobOfferResponseDTO();
        dto.setJobOfferId(jobOffer.getJobOfferId());
        dto.setJobOfferName(jobOffer.getJobOfferName());
        dto.setJobOfferDescription(jobOffer.getJobOfferDescription());
        dto.setJobOfferDate(jobOffer.getJobOfferDate().toString());
        dto.setJobOfferExpirationDate(jobOffer.getJobOfferExpirationDate().toString());
        dto.setJobOfferStatus(jobOffer.getJobOfferStatus().name());
        dto.setPositionId(jobOffer.getPosition().getPositionId());
        dto.setEmployeeId(jobOffer.getEmployee().getEmployeeId());

        return dto;
    }

    //This method return job offer by id
    public JobOfferResponseDTO getJobOfferById(Long id){
        Optional<JobOffer> jobOffer = jobOfferRepository.findById(id);
        if (jobOffer.isPresent()) {
            return convertToDTO((jobOffer.get()));
        } else {
            throw new RuntimeException("Job offer not found with id " + id);
        }
    }

    //tHIS METHOD SAVE A JOB OFFER TO THE DATABASE
    public JobOfferResponseDTO saveJobOffer(JobOfferRequestDTO request, JobOffer jobOffer) {
        mappedRequestJobOffer(request, jobOffer);
        JobOffer savedJobOffer = jobOfferRepository.save(jobOffer);
        return convertToDTO(savedJobOffer);
    }

    public void mappedRequestJobOffer(JobOfferRequestDTO request, JobOffer jobOffer) {
        jobOffer.setJobOfferName(request.getJobOfferName());
        jobOffer.setJobOfferDescription(request.getJobOfferDescription());
        jobOffer.setJobOfferDate(LocalDate.parse(request.getJobOfferDate()));
        jobOffer.setJobOfferExpirationDate(LocalDate.parse(request.getJobOfferExpirationDate()));
        jobOffer.setJobOfferStatus(JobOfferStatus.valueOf(request.getJobOfferStatus()));
        jobOffer.setPosition(findPosition(request.getPositionId()));
        jobOffer.setEmployee(findEmployee(request.getEmployeeId()));
    }

    private Position findPosition(Long positionId) {
        return positionRepository.findById(positionId).orElseThrow(() ->
                new RuntimeException("Position not found with id " + positionId));

    }

    private Employee findEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() ->
                new RuntimeException("Employee not found with id " + employeeId));
    }
}
