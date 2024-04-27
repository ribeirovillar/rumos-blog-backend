package com.blog.api.controllers;

import com.blog.api.dtos.AuthRequestDTO;
import com.blog.api.dtos.JwtResponseDTO;
import com.blog.api.dtos.UserRegistrationDTO;
import com.blog.api.mappers.UserMapper;
import com.blog.domain.services.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthServiceImpl service;
    private final UserMapper mapper;

    public AuthController(AuthServiceImpl registrationService, UserMapper mapper) {
        this.service = registrationService;
        this.mapper = mapper;
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(new JwtResponseDTO(service.login(mapper.toUser(authRequestDTO))));
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationDTO> register(@RequestBody UserRegistrationDTO dto) {
        return ResponseEntity.ok(mapper.toUserRegistrationDTO(service.register(mapper.toUser(dto))));
    }
}
