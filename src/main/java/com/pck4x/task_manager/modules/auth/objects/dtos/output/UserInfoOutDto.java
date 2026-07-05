package com.pck4x.task_manager.modules.auth.objects.dtos.output;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Basic user information")
public record UserInfoOutDto(
        @Schema(description = "User unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "User email address", example = "john@example.com")
        String email,
        @Schema(description = "User's first name", example = "John")
        String firstName,
        @Schema(description = "User's last name", example = "Doe")
        String lastName
) {
}
