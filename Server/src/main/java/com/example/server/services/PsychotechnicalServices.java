package com.example.server.services;

import com.example.server.controller.request.PsycotechnicalTestRequestDTO;
import com.example.server.entity.CandidateApplications;
import com.example.server.entity.Novelty;
import com.example.server.entity.PsychotechnicalTests;
import com.example.server.repository.ICandidateApplicationsRepository;
import com.example.server.repository.IPsychotechnicalTestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PsychotechnicalServices {
    @Autowired
    IPsychotechnicalTestsRepository psychotechnicalTestsRepository;

    @Autowired
    ICandidateApplicationsRepository candidateApplicationsRepository;

    //This method return all the psychotechnical tests
    public List<PsycotechnicalTestRequestDTO> getAllPsychotechnicalTests() {
        return psychotechnicalTestsRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private PsycotechnicalTestRequestDTO toDTO(PsychotechnicalTests psychotechnicalTests) {
        PsycotechnicalTestRequestDTO dto = new PsycotechnicalTestRequestDTO();
        dto.setPsychotechnicalTestId(psychotechnicalTests.getPsychotechnicalTestId());
        dto.setTestType(psychotechnicalTests.getTestType());
        dto.setTestDate(psychotechnicalTests.getTestDate().toString());
        dto.setTestTime(psychotechnicalTests.getTestTime());
        dto.setTestResult(psychotechnicalTests.getTestResult());
        dto.setCandidateApplicationId(psychotechnicalTests.getCandidateApplications().getCandidateApplicationId());
        return dto;
    }

    //This method return psychotechnical test by id
    public PsycotechnicalTestRequestDTO getPsychotechnicalTestById(Long id) {
        Optional<PsychotechnicalTests> novelty = psychotechnicalTestsRepository.findById(id);
        if (novelty.isPresent()) {
            return toDTO(novelty.get());
        } else {
            return null;
        }
    }

    //This method save a psychotechnical test
    public PsycotechnicalTestRequestDTO savePsychotechnicalTest(PsycotechnicalTestRequestDTO request) {
        Optional<CandidateApplications> candidateApplicationsOpt = candidateApplicationsRepository.findById(request.getCandidateApplicationId());
        if (!candidateApplicationsOpt.isPresent()) {
            throw new IllegalArgumentException("Candidate Application ID not found: " + request.getCandidateApplicationId());
        }

        CandidateApplications candidateApplications = candidateApplicationsOpt.get();

        PsychotechnicalTests psyco = new PsychotechnicalTests();
        psyco.setTestType(request.getTestType());
        psyco.setTestDate(LocalDate.parse(request.getTestDate()));
        psyco.setTestTime(request.getTestTime());
        psyco.setTestResult(request.getTestResult());
        psyco.setCandidateApplications(candidateApplications);

        PsychotechnicalTests savedPsyco = psychotechnicalTestsRepository.save(psyco);
        return toDTO(savedPsyco);
    }
}
