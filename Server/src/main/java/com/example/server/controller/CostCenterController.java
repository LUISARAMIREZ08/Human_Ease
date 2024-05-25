package com.example.server.controller;

import com.example.server.entity.CostCenter;
import com.example.server.services.CostCenterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cost-center")
public class CostCenterController {
    @Autowired
    private CostCenterServices costCenterServices;

    @GetMapping
    public List<CostCenter> getAllCostCenters() {
        return this.costCenterServices.getAllCostCenters();
    }

    @GetMapping(path = "/{id}")
    public Optional<CostCenter> getCostCenterById(@PathVariable("id") Long id) {
        return this.costCenterServices.getCostCenterById(id);
    }

    @PostMapping
    public CostCenter saveCostCenter(@RequestBody CostCenter request) {
        return this.costCenterServices.saveCostCenter(request.getCostCenterId(), request.getCostCenterName());
    }

    @PutMapping(path = "/{id}")
    public CostCenter updateCostCenterById(@RequestBody CostCenter request, @PathVariable Long id) {
        return this.costCenterServices.updateCostCenterById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteCostCenter(@PathVariable("id") Long id) {
        boolean ok = this.costCenterServices.deleteCostCenter(id);
        if (ok) {
            return "Cost center with id: " + id + " deleted";
        } else {
            return "Cost center with id: " + id + " not deleted";
        }
    }
}
