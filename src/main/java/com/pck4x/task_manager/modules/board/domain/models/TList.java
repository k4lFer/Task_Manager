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

    public static TList create(UUID boardId, String name, Integer position) {
        return TList.builder()
                .id(UUID.randomUUID())
                .boardId(boardId)
                .name(name)
                .position(position)
                .createdAt(Instant.now())
                .updatedAt(null)
                .build();
    }
}
