package com.pck4x.task_manager.modules.task.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class TCardAssignments {
    private UUID id;
    private UUID userId;
    private UUID cardsId;
    private Instant assignedAt;
}
