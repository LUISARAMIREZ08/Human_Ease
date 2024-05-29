package com.example.server.services;

import com.example.server.entity.Periods;
import com.example.server.repository.IPeriodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodsServices {
    @Autowired
    IPeriodsRepository periodsRepository;

    //This method return all periods
    public List<Periods> getAllPeriods() {
        return periodsRepository.findAll();
    }

    //This method return a period by id
    public Periods getPeriodById(Long id) {
        return periodsRepository.findById(id).orElse(null);
    }

    //This method save a period
    public Periods savePeriod(Periods period) {
        return periodsRepository.save(period);
    }
}
