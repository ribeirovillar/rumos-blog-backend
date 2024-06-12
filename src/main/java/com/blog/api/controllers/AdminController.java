package com.blog.api.controllers;

import com.blog.api.dtos.UserRegistrationDTO;
import com.blog.api.mappers.UserMapper;
import com.blog.data.models.Role;
import com.blog.domain.services.AdminServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/user-id/{userId}/roles")
    public ResponseEntity<List<String>> getUserRoles(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(service.getUserRoles(userId).stream().map(Role::getName).toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserRegistrationDTO>> getUsers() {
        return ResponseEntity.ok(service.getUsers().stream().map(mapper::toUserRegistrationDTO).toList());
    }
}
