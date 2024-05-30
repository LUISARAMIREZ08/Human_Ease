package com.example.server.services;

import com.example.server.controller.request.CandidateApplicationRequestDTO;
import com.example.server.controller.request.NoveltyRequestDTO;
import com.example.server.entity.CandidateApplications;
import com.example.server.entity.Novelty;
import com.example.server.entity.enums.ApplicationStatus;
import com.example.server.repository.ICandidateApplicationsRepository;
import com.example.server.repository.IJobOfferRepository;
import com.example.server.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateApplicationsServices {
    @Autowired
    ICandidateApplicationsRepository candidateApplicationsRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IJobOfferRepository jobOfferRepository;

    //This method return all candidate applications
    public List<CandidateApplicationRequestDTO> getAllCandidateApplications() {
        return candidateApplicationsRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private CandidateApplicationRequestDTO toDTO(CandidateApplications candidateApplications) {
        CandidateApplicationRequestDTO dto = new CandidateApplicationRequestDTO();
        dto.setCandidateApplicationId(candidateApplications.getCandidateApplicationId());
        dto.setApplicationDate(candidateApplications.getApplicationDate().toString());
        dto.setApplicationStatus(String.valueOf(candidateApplications.getApplicationStatus()));
        dto.setUserEntity(candidateApplications.getUserEntity().getCardId());
        dto.setJobOffer(candidateApplications.getJobOffer().getJobOfferId());
        return dto;
    }

    //This method return candidate application by id
    public CandidateApplicationRequestDTO getCandidateApplicationById(Long id){
        Optional<CandidateApplications> candidateApplications = candidateApplicationsRepository.findById(id);
        if (candidateApplications.isPresent()) {
            return toDTO(candidateApplications.get());
        } else {
            return null;
        }
    }

    //This method save candidate application
    public CandidateApplicationRequestDTO saveCandidateApplication(CandidateApplicationRequestDTO candidateApplicationRequestDTO) {
        if (candidateApplicationRequestDTO.getCandidateApplicationId() != null) {
            Optional<CandidateApplications> candidateApplications = candidateApplicationsRepository.findById(candidateApplicationRequestDTO.getCandidateApplicationId());
            if (candidateApplications.isPresent()) {
                return toDTO(candidateApplications.get());
            }
        }
        CandidateApplications candidateApplications = new CandidateApplications();
        candidateApplications.setApplicationDate(LocalDate.parse(candidateApplicationRequestDTO.getApplicationDate()));
        candidateApplications.setApplicationStatus(ApplicationStatus.valueOf(candidateApplicationRequestDTO.getApplicationStatus()));
        candidateApplications.setUserEntity(userRepository.findById(candidateApplicationRequestDTO.getUserEntity()).get());
        candidateApplications.setJobOffer(jobOfferRepository.findById(candidateApplicationRequestDTO.getJobOffer()).get());
        return toDTO(candidateApplicationsRepository.save(candidateApplications));
    }

}
