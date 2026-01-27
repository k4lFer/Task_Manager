package com.pck4x.task_manager.modules.board.domain.models;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class TList {
    private UUID id;
    private UUID boardId;
    private String name;
    private Integer position;
    private Instant createdAt;
    private Instant updatedAt;
}
