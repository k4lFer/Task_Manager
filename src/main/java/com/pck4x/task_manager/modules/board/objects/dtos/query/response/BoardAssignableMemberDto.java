package com.pck4x.task_manager.modules.board.objects.dtos.query.response;

import java.util.UUID;

public record BoardAssignableMemberDto(
    UUID userId,
    String fullName,
    boolean isBoardMember,
    boolean itsYou,
    String workspaceRole
) {}
