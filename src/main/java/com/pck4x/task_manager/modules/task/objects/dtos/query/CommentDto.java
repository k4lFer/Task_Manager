package com.pck4x.task_manager.modules.task.objects.dtos.query;

import java.time.Instant;
import java.util.UUID;

public record CommentDto(
        UUID id,
        UUID cardsId,
        UUID userId,
        String userName,
        String content,
        Instant createdAt,
        Instant updatedAt
) {
}
