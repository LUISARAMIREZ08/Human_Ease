package com.example.server.services;

import com.example.server.repository.ICertificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificationsServices {
    @Autowired
    ICertificationsRepository certificationsRepository;
}
