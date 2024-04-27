package com.blog.api.dtos;

import com.blog.domain.enums.CategoryEnum;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record PostDTO(UUID id, @NotNull String title, @NotNull String content, LocalDateTime created, CategoryEnum[] categories,
                      AuthorDTO author, Set<CommentDTO> comments) {
}
