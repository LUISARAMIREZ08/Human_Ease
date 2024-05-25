package com.example.server.controller;

import com.example.server.controller.request.AuthResponseDTO;
import com.example.server.controller.request.LoginRequestDTO;
import com.example.server.controller.request.RegisterRequestDTO;
import com.example.server.services.AuthService;
import com.example.server.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserServices personServices;

    @Autowired
    private AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request)
    {
        return ResponseEntity.ok(authService.register(request));
    }


}
