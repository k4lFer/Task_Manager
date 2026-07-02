package com.pck4x.task_manager.modules.task.objects.dtos.query;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record CardDetailDto(
        UUID id,
        UUID listsId,
        String title,
        String description,
        Integer position,
        Instant startDate,
        Instant dueDate,
        Instant completedAt,
        BigDecimal progress,
        List<Object> members,
        List<Object> labels,
        int commentsCount,
        int attachmentsCount,
        Instant createdAt,
        Instant updatedAt
) {
}
