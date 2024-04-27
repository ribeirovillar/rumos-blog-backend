package com.blog.api.controllers;

import com.blog.api.dtos.PostDTO;
import com.blog.api.mappers.PostMapper;
import com.blog.data.models.Post;
import com.blog.domain.services.PostServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public Page<PostDTO> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return service.findAll(PageRequest.of(page, size)).map(mapper::toPostDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<PostDTO> save(@RequestBody PostDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toPostDTO(service.save(mapper.toPost(request))));
    }

}
