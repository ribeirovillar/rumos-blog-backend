package com.blog.api.dtos;

import java.time.LocalDateTime;

public record ErrorResponseDTO(int status, String error, String message, LocalDateTime timestamp) {
    public ErrorResponseDTO(int status, String error, String message) {
        this(status, error, message, LocalDateTime.now());
    }
}
