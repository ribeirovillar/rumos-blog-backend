package com.blog.api.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentDTO(UUID id, String content, AuthorDTO author, UUID postId, LocalDateTime created) {
}
