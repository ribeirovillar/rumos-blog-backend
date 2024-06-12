package com.blog.api.controllers;

import com.blog.api.dtos.PostDTO;
import com.blog.api.mappers.PostMapper;
import com.blog.domain.services.PostServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostServiceImpl service;
    private final PostMapper mapper;

    public PostController(PostServiceImpl service, PostMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    public Page<PostDTO> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "created") String sortBy,
                                 @RequestParam(defaultValue = "desc") String order) {
        return service.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sortBy))).map(mapper::toPostDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<PostDTO> save(@Validated @RequestBody PostDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toPostDTO(service.save(mapper.toPost(request))));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> update(@PathVariable("id") UUID id, @Validated @RequestBody PostDTO request) {
        return ResponseEntity.ok(mapper.toPostDTO(service.update(id, mapper.toPost(request))));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(mapper.toPostDTO(service.findById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
