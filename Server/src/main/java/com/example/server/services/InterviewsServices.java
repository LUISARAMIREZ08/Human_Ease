package com.example.server.services;

import com.example.server.repository.IInterviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewsServices {
    @Autowired
    IInterviewsRepository interviewsRepository;
}
