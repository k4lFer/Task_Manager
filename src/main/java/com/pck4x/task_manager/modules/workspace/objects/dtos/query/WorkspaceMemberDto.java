package com.pck4x.task_manager.modules.workspace.objects.dtos.query;

import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceMemberRole;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

public record WorkspaceMemberDto(
        UUID userId,
        String fullName,
        WorkspaceMemberRole role,
        Boolean itsYou,
        Instant joinedAt
) {}
