package com.pck4x.task_manager.modules.task.objects.dtos.query.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Member assigned to a card")
public record CardMemberResponseDto(
        @Schema(description = "User unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID userId,
        @Schema(description = "Full name of the assigned user", example = "John Doe")
        String userName
) {
}
