package com.pck4x.task_manager.modules.workspace.domain.models;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class TWorkspace {
    private UUID id;
    private UUID ownerId;
    private String name;
    private String description;
    private Boolean isPrivate;
    private Instant createdAt;
    private Instant updatedAt;

    @Builder.Default
    private List<TWorkspaceMembers> workspaceMember = new java.util.ArrayList<>();

    public static TWorkspace create(UUID ownerId, String name, String description, Boolean isPrivate)
    {
        return TWorkspace.builder()
                .id(UUID.randomUUID())
                .ownerId(ownerId)
                .name(name)
                .description(description)
                .isPrivate(isPrivate)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public void attachWorkspace(TWorkspaceMembers member) {
        this.workspaceMember.add(member);
        this.updatedAt = Instant.now();
    }

}
