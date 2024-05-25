package com.example.server.services;

import com.example.server.entity.CostCenter;
import com.example.server.repository.ICostCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostCenterServices {
    @Autowired
    ICostCenterRepository costCenterRepository;

    //This method return all the cost centers in the database
    public List<CostCenter> getAllCostCenters() {
        return costCenterRepository.findAll();
    }
    //This method return a cost center by id
    public Optional<CostCenter> getCostCenterById(Long id) {
        return costCenterRepository.findById(id);
    }
    //This method saves a cost center to the database
    public CostCenter saveCostCenter(Long costCenterId, String costCenterName) {
        CostCenter costCenter = new CostCenter();
        costCenter.setCostCenterId(costCenterId);
        costCenter.setCostCenterName(costCenterName);
        return costCenterRepository.save(costCenter);
    }
    //This method updates a cost center by id
    public CostCenter updateCostCenterById(CostCenter request, Long id) {
        CostCenter costCenter = costCenterRepository.findById(id).get();
        costCenter.setCostCenterName(request.getCostCenterName());
        return costCenterRepository.save(costCenter);
    }
    //This method deletes a cost center by id
    public Boolean deleteCostCenter(Long id) {
        try {
            costCenterRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
