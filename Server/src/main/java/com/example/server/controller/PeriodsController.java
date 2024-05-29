package com.example.server.controller;

import com.example.server.entity.Periods;
import com.example.server.services.PeriodsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/periods")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PeriodsController {

    @Autowired
    private PeriodsServices periodsServices;

    @GetMapping
    public List<Periods> getAllPeriods() {
        return this.periodsServices.getAllPeriods();
    }

    @PostMapping
    public Periods savePeriod(@RequestBody Periods period) {
        return this.periodsServices.savePeriod(period);
    }


}
