package com.example.server.controller;

import com.example.server.controller.request.PsycotechnicalTestRequestDTO;
import com.example.server.entity.PsychotechnicalTests;
import com.example.server.services.PsychotechnicalServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/psyco")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PsycoController {

    @Autowired
    PsychotechnicalServices psychotechnicalServices;

    @GetMapping
    public List<PsycotechnicalTestRequestDTO> getAllPsychotechnicalTests() {
        return psychotechnicalServices.getAllPsychotechnicalTests();
    }

    @GetMapping("/{id}")
    public PsycotechnicalTestRequestDTO getPsychotechnicalTestById(@PathVariable Long id) {
        return psychotechnicalServices.getPsychotechnicalTestById(id);
    }

    @PostMapping
    public PsycotechnicalTestRequestDTO savePsychotechnicalTest(@RequestBody PsycotechnicalTestRequestDTO request) {
        return psychotechnicalServices.savePsychotechnicalTest(request);
    }
}
