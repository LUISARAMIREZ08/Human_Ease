package com.example.server.controller;

import com.example.server.controller.request.NoveltyRequestDTO;
import com.example.server.entity.Novelty;
import com.example.server.services.NoveltyServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/novelty")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NoveltyController {

    @Autowired
    NoveltyServices noveltyServices;

    @GetMapping
    public List<NoveltyRequestDTO> getAllNovelties() {
        return noveltyServices.getAllNovelties();
    }

    @GetMapping("/{id}")
    public NoveltyRequestDTO getNoveltyById(@PathVariable Long id){
        return noveltyServices.getNoveltyById(id);
    }

    @PostMapping
    public NoveltyRequestDTO saveNovelty(@RequestBody NoveltyRequestDTO request) {
        return noveltyServices.saveNovelty(request, new Novelty());
    }
}
