package com.example.server.services;

import com.example.server.controller.request.CandidateApplicationRequestDTO;
import com.example.server.controller.request.CandidateApplicationUpdateRequestDTO;
import com.example.server.controller.request.NoveltyRequestDTO;
import com.example.server.entity.CandidateApplications;
import com.example.server.entity.JobOffer;
import com.example.server.entity.Novelty;
import com.example.server.entity.UserEntity;
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
        //si el usuario existe y el jobOffer existe
        // Verifica si el usuario y la oferta de trabajo existen
        UserEntity userEntity = userRepository.findById(candidateApplicationRequestDTO.getUserEntity())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + candidateApplicationRequestDTO.getUserEntity()));

        JobOffer jobOffer = jobOfferRepository.findById(candidateApplicationRequestDTO.getJobOffer())
                .orElseThrow(() -> new IllegalArgumentException("Job offer not found: " + candidateApplicationRequestDTO.getJobOffer()));

        // Verifica si el usuario ya ha aplicado para esta oferta de trabajo
        List<CandidateApplications> existingApplications = candidateApplicationsRepository.findCandidateApplicationsByJobOffer(jobOffer, userEntity);
        if (!existingApplications.isEmpty()) {
            throw new RuntimeException("The user has already applied for this job offer");
        }
        CandidateApplications candidateApplicationsNew = new CandidateApplications();
        candidateApplicationsNew.setApplicationDate(LocalDate.parse(candidateApplicationRequestDTO.getApplicationDate()));
        candidateApplicationsNew.setApplicationStatus(ApplicationStatus.valueOf(candidateApplicationRequestDTO.getApplicationStatus()));
        candidateApplicationsNew.setUserEntity(userRepository.findById(candidateApplicationRequestDTO.getUserEntity()).get());
        candidateApplicationsNew.setJobOffer(jobOfferRepository.findById(candidateApplicationRequestDTO.getJobOffer()).get());
        return toDTO(candidateApplicationsRepository.save(candidateApplicationsNew));
    }


    // Este método actualiza el estado de la aplicación de un candidato
    public CandidateApplicationRequestDTO updateApplicationStatus(CandidateApplicationUpdateRequestDTO request) {
        CandidateApplications candidateApplication = candidateApplicationsRepository.findById(request.getCandidateApplicationId())
                .orElseThrow(() -> new RuntimeException("Candidate Application not found"));

        candidateApplication.setApplicationStatus(ApplicationStatus.valueOf(request.getApplicationStatus()));
        CandidateApplications updatedCandidateApplication = candidateApplicationsRepository.save(candidateApplication);
        return toDTO(updatedCandidateApplication);
    }
}
