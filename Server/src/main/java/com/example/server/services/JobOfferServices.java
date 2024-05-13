package com.example.server.services;

import com.example.server.repository.IJobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobOfferServices {
    @Autowired
    IJobOfferRepository jobOfferRepository;
}
