package com.blog.api.controllers;

import com.blog.api.dtos.UserRegistrationDTO;
import com.blog.api.mappers.UserMapper;
import com.blog.domain.services.AdminServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminServiceImpl service;
    private final UserMapper mapper;

    public AdminController(AdminServiceImpl service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/promote/{userId}")
    public ResponseEntity<UserRegistrationDTO> promoteUserAdmin(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(mapper.toUserRegistrationDTO(service.promoteUserAdmin(userId)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/revoke/{userId}")
    public ResponseEntity<UserRegistrationDTO> revokeUserAdmin(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(mapper.toUserRegistrationDTO(service.revokeUserAdmin(userId)));
    }
}
