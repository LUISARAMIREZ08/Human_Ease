package com.example.server.services;

import com.example.server.controller.request.RecordUserRequestDTO;
import com.example.server.entity.CandidateApplications;
import com.example.server.entity.RecordUser;
import com.example.server.repository.ICandidateApplicationsRepository;
import com.example.server.repository.IRecordPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecordPersonServices {
    @Autowired
    IRecordPersonRepository recordPersonRepository;

    @Autowired
    ICandidateApplicationsRepository candidateApplicationsRepository;

    //This method return all the records of a person
    public List<RecordUserRequestDTO> getRecords(){
        return recordPersonRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private RecordUserRequestDTO toDTO(RecordUser recordUser) {
        RecordUserRequestDTO dto = new RecordUserRequestDTO();
        dto.setRecordId(recordUser.getRecordId());
        dto.setRecordType(recordUser.getRecordType());
        dto.setRecordResult(recordUser.getRecordResult());
        dto.setRecordDateVerified(recordUser.getRecordDateVerified());
        dto.setCandidateApplicationId(recordUser.getCandidateApplications().getCandidateApplicationId());
        return dto;
    }

    //This method return record by id
    public RecordUserRequestDTO getRecordById(Long id){
        RecordUser recordUser = recordPersonRepository.findById(id).get();
        return toDTO(recordUser);
    }

    //This method save a record
    public RecordUserRequestDTO saveRecord(RecordUserRequestDTO request, RecordUser recordUser){
        recordUser.setRecordType(request.getRecordType());
        recordUser.setRecordResult(request.getRecordResult());
        recordUser.setRecordDateVerified(request.getRecordDateVerified());
        recordUser.setCandidateApplications(findAplication(request.getCandidateApplicationId()));
        RecordUser recordUserSaved = recordPersonRepository.save(recordUser);
        return toDTO(recordUserSaved);
    }

    private CandidateApplications findAplication(Long candidateApplicationId) {
        Optional<CandidateApplications> candidateApplications =
                candidateApplicationsRepository.findById(candidateApplicationId);
        return candidateApplications.get();
    }
}
