package com.pck4x.task_manager.modules.task.objects.dtos.query.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Label attached to a card")
public record CardLabelResponseDto(
        @Schema(description = "Label unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "Label name", example = "Bug")
        String name,
        @Schema(description = "Label color hex code", example = "#FF0000")
        String color
) {
}
