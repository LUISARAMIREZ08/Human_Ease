package com.example.server.controller;

import com.example.server.controller.request.JobOfferRequestDTO;
import com.example.server.controller.response.JobOfferJoin;
import com.example.server.controller.response.JobOfferResponseDTO;
import com.example.server.entity.JobOffer;
import com.example.server.services.JobOfferServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job-offer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class JobOfferController {

    @Autowired
    private JobOfferServices jobOfferServices;

    @GetMapping
    public List<JobOfferResponseDTO> getAllJobOffers() {
        return this.jobOfferServices.getAllJobOffers();
    }

    @GetMapping(value = "/{id}")
    public JobOfferResponseDTO getJobOfferById(@PathVariable Long id) {
        return this.jobOfferServices.getJobOfferById(id);
    }

    @PostMapping
    public JobOfferResponseDTO saveJobOffer(@RequestBody JobOfferRequestDTO request) {
        return this.jobOfferServices.saveJobOffer(request, new JobOffer());
    }

    //Join query
    @GetMapping(path = "/all")
    public ResponseEntity<List<JobOfferJoin>> getAllJobOffersAndEmployees() {
        List<JobOfferJoin> jobOffers = jobOfferServices.findJobOfferDetails();
        return ResponseEntity.ok(jobOffers);
    }

    //Join query for id
    @GetMapping(path = "/all/{id}")
    public ResponseEntity<List<JobOfferJoin>> getAllJobOffersAndEmployeesById(@PathVariable Long id) {
        List<JobOfferJoin> jobOffers = jobOfferServices.getEmployeeAndUserAndPosition(id);
        return ResponseEntity.ok(jobOffers);
    }
}
