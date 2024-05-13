package com.example.server.services;

import com.example.server.repository.IPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServices {
    @Autowired
    IPositionRepository positionRepository;
}
