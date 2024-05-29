package com.example.server.controller;

import com.example.server.controller.request.CertificationsRequestDTO;
import com.example.server.controller.response.CertificationsResponseDTO;
import com.example.server.entity.Certifications;
import com.example.server.services.CertificationsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certifications")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CertificationsController {

    @Autowired
    private CertificationsServices certificationsServices;

    @GetMapping
    public List<CertificationsResponseDTO> getAllCertifications() {
        return this.certificationsServices.getAllCertifications();
    }

    @GetMapping(value = "/{id}")
    public CertificationsResponseDTO getCertificationById(@PathVariable Long id) {
        return this.certificationsServices.getCertificationById(id);
    }

    @PostMapping
    public CertificationsResponseDTO saveCertification(@RequestBody CertificationsRequestDTO request) {
        return this.certificationsServices.saveCertification(request, new Certifications());
    }
}
