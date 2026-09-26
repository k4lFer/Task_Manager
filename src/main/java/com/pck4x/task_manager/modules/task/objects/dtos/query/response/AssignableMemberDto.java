package com.pck4x.task_manager.modules.task.objects.dtos.query.response;

import java.util.UUID;

public record AssignableMemberDto(
    UUID userId,
    String fullName,
    boolean isAssigned,
    boolean itsYou,
    String workspaceRole,
    boolean canManageBoard
) {}
