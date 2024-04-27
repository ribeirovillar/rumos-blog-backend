package com.blog.domain.exceptions;

import java.util.UUID;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(UUID id) {
        super(String.format("Post with id '%s' not found", id));
    }
}
