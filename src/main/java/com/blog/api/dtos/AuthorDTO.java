package com.blog.api.dtos;

import java.util.UUID;

public record AuthorDTO(UUID id, String name, String email) {
}
