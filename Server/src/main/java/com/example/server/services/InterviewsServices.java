package com.example.server.services;

import com.example.server.controller.request.InterviewRequestDTO;
import com.example.server.controller.request.InterviewUpdateRequestDTO;
import com.example.server.controller.response.InterviewResponseDTO;
import com.example.server.entity.CandidateApplications;
import com.example.server.entity.Interviews;
import com.example.server.entity.enums.InterviewStatus;
import com.example.server.entity.enums.InterviewType;
import com.example.server.repository.ICandidateApplicationsRepository;
import com.example.server.repository.IInterviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterviewsServices {
    @Autowired
    IInterviewsRepository interviewsRepository;

    @Autowired
    ICandidateApplicationsRepository candidateApplicationsRepository;

    //This method return all interviews
    public List<InterviewRequestDTO> getAllInterviews() {
        return interviewsRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private InterviewRequestDTO toDTO(Interviews interviews) {
        InterviewRequestDTO dto = new InterviewRequestDTO();
        dto.setInterviewId(interviews.getInterviewId());
        dto.setInterviewDate(interviews.getInterviewDate().toString());
        dto.setInterviewTime(interviews.getInterviewTime());
        dto.setResult(interviews.getResult());
        dto.setLink(interviews.getLink());
        dto.setInterviewStatus(interviews.getInterviewStatus().name());
        dto.setInterviewType(interviews.getInterviewType().name());
        dto.setCandidateApplicationId(interviews.getCandidateApplications().getCandidateApplicationId());
        return dto;
    }
    //This method return interview by id
    public InterviewRequestDTO getInterviewById(Long id){
        Optional<Interviews> interviews = interviewsRepository.findById(id);
        if (interviews.isPresent()) {
            return toDTO(interviews.get());
        }
        return null;
    }

    //This method save a new interview
    public InterviewRequestDTO saveInterview(InterviewRequestDTO request, Interviews interviews) {
        interviews.setInterviewDate(LocalDate.parse(request.getInterviewDate()));
        interviews.setInterviewTime(request.getInterviewTime());
        interviews.setResult(request.getResult());
        interviews.setLink(request.getLink());
        interviews.setInterviewStatus(InterviewStatus.valueOf(request.getInterviewStatus()));
        interviews.setInterviewType(InterviewType.valueOf(request.getInterviewType()));
        interviews.setCandidateApplications(findApplication(request.getCandidateApplicationId()));
        Interviews saveInterview = interviewsRepository.save(interviews);
        return toDTO(saveInterview);
    }

    private CandidateApplications findApplication(Long candidateApplicationId) {
        return candidateApplicationsRepository.findById(candidateApplicationId)
                .orElseThrow(() -> new RuntimeException("Candidate Application not found"));
    }

    public InterviewResponseDTO updateInterviewByCandidateApplication(InterviewUpdateRequestDTO request) {
        Optional<Interviews> optionalInterview = interviewsRepository.findByCandidateApplicationId(request.getCandidateApplicationId());

        if (optionalInterview.isPresent()) {
            Interviews interview = optionalInterview.get();
            interview.setResult(request.getResult());
            interview.setInterviewStatus(InterviewStatus.valueOf(request.getInterviewStatus()));
            Interviews updatedInterview = interviewsRepository.save(interview);
            return toResponseDTO(updatedInterview);
        } else {
            throw new RuntimeException("Interview not found for the provided Candidate Application");
        }
    }

    private InterviewResponseDTO toResponseDTO(Interviews interview) {
        return new InterviewResponseDTO(
                interview.getInterviewId(),
                interview.getInterviewDate().toString(),
                interview.getInterviewTime(),
                interview.getResult(),
                interview.getLink(),
                interview.getInterviewStatus().name(),
                interview.getInterviewType().name(),
                interview.getCandidateApplications().getCandidateApplicationId()
        );
    }
}
