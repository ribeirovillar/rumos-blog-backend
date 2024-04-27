package com.blog.api.dtos;

import java.util.UUID;

public record CommentDTO(UUID id, String content, AuthorDTO author, PostDTO post) {
}
