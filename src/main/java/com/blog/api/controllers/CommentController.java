package com.blog.api.controllers;

import com.blog.api.dtos.CommentDTO;
import com.blog.api.mappers.CommentMapper;
import com.blog.domain.services.CommentServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> save(@PathVariable("postId") UUID postId, @RequestBody CommentDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toCommentDTO(service.save(postId, mapper.toComment(request))));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    public Page<CommentDTO> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return service.findAll(PageRequest.of(page, size)).map(mapper::toCommentDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toCommentDTO(service.findById(id)));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable("id") UUID id, @RequestBody CommentDTO request) {
        return ResponseEntity.ok(mapper.toCommentDTO(service.update(id, mapper.toComment(request))));
    }
}
