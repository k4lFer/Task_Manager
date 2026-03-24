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
}
