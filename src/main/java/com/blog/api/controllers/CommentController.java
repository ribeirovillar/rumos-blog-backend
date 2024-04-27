package com.blog.api.controllers;

import com.blog.api.dtos.CommentDTO;
import com.blog.api.mappers.CommentMapper;
import com.blog.domain.services.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/comments")
public class CommentController {

    private final CommentServiceImpl service;
    private final CommentMapper mapper;

    public CommentController(CommentServiceImpl service, CommentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping
    public ResponseEntity<CommentDTO> save(@RequestBody CommentDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toCommentDTO(service.save(mapper.toComment(request))));
    }
}
