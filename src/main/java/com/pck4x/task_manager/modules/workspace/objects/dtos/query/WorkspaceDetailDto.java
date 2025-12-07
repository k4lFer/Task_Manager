package com.pck4x.task_manager.modules.workspace.objects.dtos.query;

import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class WorkspaceDetailDto {
    private UUID id;
    private String name;
    private String description;
    private Boolean isPrivate;

    // Info del Dueño
    private UUID ownerId;
    private String ownerName;
    private String ownerEmail;

    // Lista de miembros con sus nombres
    private List<WorkspaceMemberDto> members;

    private Instant createdAt;
}