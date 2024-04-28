package com.blog.domain.exceptions;

import java.util.UUID;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(UUID id) {
        super(String.format("Comment with id '%s' not found", id));
    }
}
