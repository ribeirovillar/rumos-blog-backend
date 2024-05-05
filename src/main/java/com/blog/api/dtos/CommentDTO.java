package com.blog.api.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentDTO(UUID id, @NotNull String content, AuthorDTO author, @NotNull UUID postId, LocalDateTime created) {
}
