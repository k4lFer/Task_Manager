package com.pck4x.task_manager.modules.board.domain.models;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class TLabel {
    private UUID id;
    private String name;
    private String color;
    private UUID boardId;
    private Instant createdAt;
    private Instant updatedAt;

    public static TLabel create(String name, String color, UUID boardId) {
        return TLabel.builder()
                .id(UUID.randomUUID())
                .name(name)
                .color(color)
                .boardId(boardId)
                .createdAt(Instant.now())
                .updatedAt(null)
                .build();
    }

}
