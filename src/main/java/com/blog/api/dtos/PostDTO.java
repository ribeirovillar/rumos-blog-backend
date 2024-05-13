package com.blog.api.dtos;

import com.blog.domain.enums.CategoryEnum;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record PostDTO(UUID id, String title, String content, LocalDateTime created, LocalDateTime updated,
                      CategoryEnum[] categories,
                      AuthorDTO author, Set<CommentDTO> comments) {
}
