package com.example.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.example.server.Model.MailStructure;
import com.example.server.services.MailService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
@RequestMapping("/mail")
@CrossOrigin(origins = "http://localhost:4200")
public class MailController {

    @Autowired
    private MailService mailService;
    @PostMapping("/send/{mail}")
    public ResponseEntity<Map<String, String>> sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure) {
        mailService.sendMail(mail, mailStructure);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Email enviado exitosamente");
        return ResponseEntity.ok(response);
    }
}
