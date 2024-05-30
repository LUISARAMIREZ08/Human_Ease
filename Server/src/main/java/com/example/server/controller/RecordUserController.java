package com.example.server.controller;

import com.example.server.controller.request.RecordUserRequestDTO;
import com.example.server.entity.RecordUser;
import com.example.server.services.RecordPersonServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/record-user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RecordUserController {

    @Autowired
    RecordPersonServices recordPersonServices;

    @GetMapping
    public List<RecordUserRequestDTO> getRecords(){
        return recordPersonServices.getRecords();
    }

    @GetMapping("/{id}")
    public RecordUserRequestDTO getRecordById(Long id){
        return recordPersonServices.getRecordById(id);
    }

    @PostMapping
    public RecordUserRequestDTO saveRecord(@RequestBody RecordUserRequestDTO request){
        return recordPersonServices.saveRecord(request, new RecordUser());
    }
}
