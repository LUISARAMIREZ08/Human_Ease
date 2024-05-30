package com.example.server.controller;

import com.example.server.controller.request.PositionRequestDTO;
import com.example.server.entity.Position;
import com.example.server.services.PositionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/position")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PositionController {

    @Autowired
    private PositionServices positionServices;

    @GetMapping
    public List<Position> getAllPositions() {
        return this.positionServices.getAllPositions();
    }

    @GetMapping(path = "/{id}")
    public Optional<Position> getPositionById(@PathVariable Long id) {
        return this.positionServices.getPositionById(id);
    }

    @PostMapping
    public PositionRequestDTO savePosition(@RequestBody PositionRequestDTO positionRequestDTO) {
        return this.positionServices.savePosition(positionRequestDTO);
    }
}
