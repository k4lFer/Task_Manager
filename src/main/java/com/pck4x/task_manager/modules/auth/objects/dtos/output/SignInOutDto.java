package com.pck4x.task_manager.modules.auth.objects.dtos.output;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Response returned after successful authentication (login/register)")
public record SignInOutDto(
        @Schema(description = "User unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "Username", example = "johndoe")
        String userName,
        @Schema(description = "User's first name", example = "John")
        String firstName,
        @Schema(description = "User's last name", example = "Doe")
        String lastName,
        @Schema(description = "JWT access token for API authentication")
        String accessToken,

        @JsonIgnore
        @Schema(hidden = true)
        String refreshToken
) {

}
