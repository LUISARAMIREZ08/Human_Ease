package com.example.server.controller;

import com.example.server.controller.response.ContractsResponseDTO;
import com.example.server.controller.request.ContratcsRequestDTO;
import com.example.server.entity.Contratcs;
import com.example.server.services.ContractsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ContratcsController {

    @Autowired
    private ContractsServices contractsServices;

    @GetMapping
    public List<Contratcs> getAllContracts() {
        return this.contractsServices.getAllContracts();
    }

    @GetMapping(path = "/{id}")
    public Optional<Contratcs> getContractById(Long id) {
        return this.contractsServices.getContractById(id);
    }

    @PostMapping
    public ContractsResponseDTO saveContract(@RequestBody ContratcsRequestDTO request) {
        return this.contractsServices.saveContract(request, new Contratcs());
    }
}
