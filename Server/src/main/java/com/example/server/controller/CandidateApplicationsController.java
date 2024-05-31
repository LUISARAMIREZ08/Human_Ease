package com.example.server.controller;

import com.example.server.controller.request.CandidateApplicationRequestDTO;
import com.example.server.controller.request.CandidateApplicationUpdateRequestDTO;
import com.example.server.entity.CandidateApplications;
import com.example.server.services.CandidateApplicationsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidateApplications")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CandidateApplicationsController {

    @Autowired
    private CandidateApplicationsServices candidateApplicationsServices;

    @GetMapping
    public List<CandidateApplicationRequestDTO> getAllCandidateApplications() {
        return this.candidateApplicationsServices.getAllCandidateApplications();
    }

    @GetMapping(path = "/{id}")
    public CandidateApplicationRequestDTO getCandidateApplicationById(@PathVariable Long id) {
        return this.candidateApplicationsServices.getCandidateApplicationById(id);
    }

    @PostMapping
    public CandidateApplicationRequestDTO saveCandidateApplication(@RequestBody CandidateApplicationRequestDTO candidateApplicationRequestDTO) {
        return this.candidateApplicationsServices.saveCandidateApplication(candidateApplicationRequestDTO);
    }

    @PutMapping("/updateStatus")
    public CandidateApplicationRequestDTO updateApplicationStatus(@RequestBody CandidateApplicationUpdateRequestDTO request) {
        return this.candidateApplicationsServices.updateApplicationStatus(request);
    }
}
