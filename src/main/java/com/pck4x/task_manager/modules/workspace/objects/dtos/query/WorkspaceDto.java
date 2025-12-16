package com.pck4x.task_manager.modules.workspace.objects.dtos.query;

import lombok.Data;

import java.util.UUID;

@Data
public class WorkspaceDto {
    private UUID id;
    private String name;
    private String description;
    private Boolean isPrivate;
    private Boolean isOwner;

    private UUID ownerId;
    private String ownerName;
}
