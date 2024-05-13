package com.example.server.services;

import com.example.server.repository.IAffiliationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AffiliationsServices {
    @Autowired
    IAffiliationsRepository affiliationsRepository;
}
