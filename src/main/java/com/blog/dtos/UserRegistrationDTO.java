package com.blog.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record UserRegistrationDTO(UUID id, @NotNull String email, @NotNull String password, @NotNull String firstName,
                                  @NotNull String lastName, @NotNull Date birthDate, String role) {
}
