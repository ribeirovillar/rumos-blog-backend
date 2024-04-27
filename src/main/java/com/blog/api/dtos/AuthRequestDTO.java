package com.blog.api.dtos;

import jakarta.validation.constraints.NotNull;

public record AuthRequestDTO(@NotNull String email, @NotNull String password) {
}
