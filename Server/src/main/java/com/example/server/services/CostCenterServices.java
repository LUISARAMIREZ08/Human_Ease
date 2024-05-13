package com.example.server.services;

import com.example.server.repository.ICostCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostCenterServices {
    @Autowired
    ICostCenterRepository costCenterRepository;
}
