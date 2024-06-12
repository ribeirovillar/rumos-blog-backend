package com.blog.domain.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(UUID userId) {
        super("User with id " + userId + " not found");
    }
}
