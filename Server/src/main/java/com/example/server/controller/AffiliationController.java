package com.example.server.controller;

import com.example.server.controller.request.AffiliationsRequestDTO;
import com.example.server.entity.Affiliations;
import com.example.server.services.AffiliationsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/affiliation")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AffiliationController {

    @Autowired
    private AffiliationsServices affiliationsServices;

    @GetMapping
    public List<AffiliationsRequestDTO> getAllAffiliations() {
        return affiliationsServices.getAllAffiliations();
    }

    @GetMapping("/{id}")
    public AffiliationsRequestDTO getAffiliationById(@PathVariable Long id) {
        return affiliationsServices.getAffiliationById(id);
    }

    @PostMapping
    public AffiliationsRequestDTO saveAffiliation(@RequestBody AffiliationsRequestDTO request) {
        return affiliationsServices.saveAffiliation(request, new Affiliations());
    }
}
