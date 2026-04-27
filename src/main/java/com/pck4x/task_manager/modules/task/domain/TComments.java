package com.pck4x.task_manager.modules.task.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class TComments {
    private UUID id;
    private UUID cardsId;
    private UUID userId;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;

    public static TComments create(UUID cardsId, UUID userId, String content)
    {
        return TComments.builder()
                .id(UUID.randomUUID())
                .cardsId(cardsId)
                .userId(userId)
                .content(content)
                .createdAt(Instant.now())
                .updatedAt(null)
                .build();
    }
}
