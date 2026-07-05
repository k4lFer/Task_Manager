package com.pck4x.task_manager.modules.board.objects.dtos.query.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Board summary information for list views")
public record GetBoardsResponseDto(
        @Schema(description = "Board unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "Workspace ID", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID workspaceId,
        @Schema(description = "Board name", example = "Sprint Board")
        String name,
        @Schema(description = "Board description", example = "Kanban board for the current sprint")
        String description,
        @Schema(description = "Whether the authenticated user is the owner", example = "true")
        String isOwner,
        @Schema(description = "Owner's user ID", example = "550e8400-e29b-41d4-a716-446655440000")
        String ownerId,
        @Schema(description = "Owner's full name", example = "John Doe")
        String ownerName
) {
}
