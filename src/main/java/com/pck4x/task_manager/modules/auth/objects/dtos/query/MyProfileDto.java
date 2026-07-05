package com.pck4x.task_manager.modules.auth.objects.dtos.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
import java.util.UUID;

@Schema(description = "Authenticated user's profile information")
public record MyProfileDto(
        @Schema(description = "User unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID userId,
        @Schema(description = "Username", example = "johndoe")
        String username,
        @Schema(description = "User email address", example = "john@example.com")
        String email,
        @Schema(description = "User's first name", example = "John")
        String firstName,
        @Schema(description = "User's last name", example = "Doe")
        String lastName,
        @Schema(description = "User's birth date", example = "1990-01-15")
        Date birthDate
) {}