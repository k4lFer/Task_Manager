package com.pck4x.task_manager.modules.board.objects.dtos.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Board summary with member and card counts")
public record BoardSummaryDto(
        @Schema(description = "Board unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "Workspace ID", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID workspaceId,
        @Schema(description = "Board name", example = "Sprint Board")
        String name,
        @Schema(description = "Board description", example = "Kanban board for the current sprint")
        String description,
        @Schema(description = "Whether the authenticated user is the owner")
        Boolean isOwner,
        @Schema(description = "Owner's user ID", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID ownerId,
        @Schema(description = "Owner's full name", example = "John Doe")
        String ownerName,
        @Schema(description = "Number of members", example = "5")
        Long membersCount,
        @Schema(description = "Number of lists", example = "3")
        Long listsCount,
        @Schema(description = "Number of cards", example = "12")
        Long cardsCount
) {
}
