package com.pck4x.task_manager.modules.chat.domain.models;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class TChannel {
    private UUID id;
    private UUID workspaceId;
    private String name;
    private String description;
    private Instant createdAt;

    public static TChannel create(UUID workspaceId, String name, String description) {
        return TChannel.builder()
                .id(UUID.randomUUID())
                .workspaceId(workspaceId)
                .name(name)
                .description(description)
                .createdAt(Instant.now())
                .build();
    }

}
