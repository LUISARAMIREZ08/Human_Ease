package com.example.server.controller;

import com.example.server.controller.request.RequirementsRequestDTO;
import com.example.server.entity.Requirements;
import com.example.server.services.RequirementsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requierements")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RequierementsController {

    @Autowired
    private RequirementsServices requirementsServices;

    @GetMapping
    public List<Requirements> getAllRequirements() {
        return this.requirementsServices.getAllRequirements();
    }

    @GetMapping(path = "/{id}")
    public Optional<Requirements> getRequirementById(Long id) {
        return this.requirementsServices.getRequirementById(id);
    }

    @PostMapping
    public Requirements saveRequirement(@RequestBody RequirementsRequestDTO request) {
        return this.requirementsServices.saveRequirement(request.getDescriptionRequirement(), request.getPosition());
    }
}
