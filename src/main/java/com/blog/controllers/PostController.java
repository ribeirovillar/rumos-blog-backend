package com.blog.controllers;

import com.blog.models.Post;
import com.blog.services.PostServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostServiceImpl service;

    public PostController(PostServiceImpl service) {
        this.service = service;
    }

    @GetMapping()
    public Page<Post> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return service.findAll(PageRequest.of(page, size));
    }

}
