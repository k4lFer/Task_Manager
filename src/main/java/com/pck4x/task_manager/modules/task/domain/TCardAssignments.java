package com.pck4x.task_manager.modules.task.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class TCardAssignments {
    private UUID id;
    private UUID cardsId;
    private UUID userId;
    private Instant assignedAt;

    public static TCardAssignments create(UUID cardsId, UUID userId) {
        return TCardAssignments.builder()
                .id(UUID.randomUUID())
                .cardsId(cardsId)
                .userId(userId)
                .assignedAt(Instant.now())
                .build();
    }
}
