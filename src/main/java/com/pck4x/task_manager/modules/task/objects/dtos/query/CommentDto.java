package com.pck4x.task_manager.modules.task.objects.dtos.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Comment on a card")
public record CommentDto(
        @Schema(description = "Comment unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "Card ID that the comment belongs to", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID cardsId,
        @Schema(description = "Author's user ID", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID userId,
        @Schema(description = "Author's username", example = "johndoe")
        String userName,
        @Schema(description = "Comment content", example = "Great progress on this task!")
        String content,
        @Schema(description = "Creation timestamp", example = "2024-01-15T10:30:00Z")
        Instant createdAt,
        @Schema(description = "Last update timestamp", example = "2024-01-20T14:00:00Z")
        Instant updatedAt
) {
}
