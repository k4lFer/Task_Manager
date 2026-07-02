package com.pck4x.task_manager.modules.workspace.objects.dtos.query;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record WorkspaceDetailDto(
        UUID id,
        String name,
        String description,
        Boolean isPrivate,
        Boolean isOwner,
        UUID ownerId,
        String ownerName,
        List<WorkspaceMemberDto> members,
        List<WorkspaceChannelDto> channels,
        List<WorkspaceBoardDto> boards,
        Instant createdAt
) {}
