package com.pck4x.task_manager.modules.workspace.objects.dtos.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Board summary within a workspace context")
public record WorkspaceBoardDto(
        @Schema(description = "Board unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "Board name", example = "Sprint Board")
        String name,
        @Schema(description = "Board description", example = "Kanban board for the current sprint")
        String description,
        @Schema(description = "Owner's user ID", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID ownerId,
        @Schema(description = "Owner's full name", example = "John Doe")
        String ownerName,
        @Schema(description = "Number of members in the board", example = "5")
        Long membersCount,
        @Schema(description = "Number of lists in the board", example = "3")
        Long listsCount,
        @Schema(description = "Number of cards in the board", example = "12")
        Long cardsCount,
        @Schema(description = "Creation timestamp", example = "2024-01-15T10:30:00Z")
        Instant createdAt
) {

}
