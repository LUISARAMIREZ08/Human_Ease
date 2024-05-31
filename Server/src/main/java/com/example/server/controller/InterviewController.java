package com.example.server.controller;

import com.example.server.controller.request.InterviewRequestDTO;
import com.example.server.controller.request.InterviewUpdateRequestDTO;
import com.example.server.controller.response.InterviewResponseDTO;
import com.example.server.entity.Interviews;
import com.example.server.services.InterviewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interview")
@CrossOrigin(origins = "http://localhost:4200")
public class InterviewController {
    @Autowired
    private InterviewsServices interviewsServices;

    @GetMapping
    public List<InterviewRequestDTO> getAllInterviews() {
        return this.interviewsServices.getAllInterviews();
    }

    @GetMapping(path = "/{id}")
    public InterviewRequestDTO getInterviewById(@PathVariable Long id) {
        return this.interviewsServices.getInterviewById(id);
    }

    @PostMapping
    public InterviewRequestDTO saveInterview(@RequestBody InterviewRequestDTO request) {
        return this.interviewsServices.saveInterview(request, new Interviews());
    }

    @PutMapping("/update")
    public ResponseEntity<InterviewResponseDTO> updateInterview(@RequestBody InterviewUpdateRequestDTO request) {
        InterviewResponseDTO updatedInterview = interviewsServices.updateInterviewByCandidateApplication(request);
        return ResponseEntity.ok(updatedInterview);
    }
}
