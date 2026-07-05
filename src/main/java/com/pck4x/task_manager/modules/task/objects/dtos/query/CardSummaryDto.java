package com.pck4x.task_manager.modules.task.objects.dtos.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Schema(description = "Card summary information for list views")
public record CardSummaryDto(
        @Schema(description = "Card unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "List/column ID that contains the card", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID listsId,
        @Schema(description = "Card title", example = "Implement login feature")
        String title,
        @Schema(description = "Card description", example = "Implement JWT-based authentication")
        String description,
        @Schema(description = "Card position within the list", example = "1")
        Integer position,
        @Schema(description = "Start date", example = "2024-01-15T10:30:00Z")
        Instant startDate,
        @Schema(description = "Due date", example = "2024-02-01T10:30:00Z")
        Instant dueDate,
        @Schema(description = "Completion timestamp", example = "2024-01-28T15:00:00Z")
        Instant completedAt,
        @Schema(description = "Progress percentage (0-100)", example = "75.5")
        BigDecimal progress,
        @Schema(description = "Number of comments", example = "3")
        Long commentsCount,
        @Schema(description = "Number of attachments", example = "2")
        Integer attachmentsCount,
        @Schema(description = "Creation timestamp", example = "2024-01-15T10:30:00Z")
        Instant createdAt,
        @Schema(description = "Last update timestamp", example = "2024-01-20T14:00:00Z")
        Instant updatedAt
) {
}
