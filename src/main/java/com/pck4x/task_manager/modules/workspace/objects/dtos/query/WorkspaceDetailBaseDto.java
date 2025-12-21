package com.pck4x.task_manager.modules.workspace.objects.dtos.query;

import java.time.Instant;
import java.util.UUID;

public record WorkspaceDetailBaseDto(
        UUID id,
        String name,
        String description,
        Boolean isPrivate,
        Boolean isOwner,
        UUID ownerId,
        String ownerName,
        Instant createdAt
) {}