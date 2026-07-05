package com.pck4x.task_manager.modules.board.objects.dtos.query.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Board list/column information")
public record BoardListResponseDto(
        @Schema(description = "List unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "List name", example = "To Do")
        String name,
        @Schema(description = "List position within the board", example = "1")
        Integer position
) {
}
