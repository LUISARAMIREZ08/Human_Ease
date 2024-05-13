package com.example.server.services;

import com.example.server.repository.INoveltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoveltyServices {
    @Autowired
    INoveltyRepository noveltyRepository;
}
