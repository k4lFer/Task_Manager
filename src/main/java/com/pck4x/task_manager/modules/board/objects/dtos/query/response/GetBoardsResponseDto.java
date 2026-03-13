package com.pck4x.task_manager.modules.board.objects.dtos.query.response;

import java.util.UUID;

public record GetBoardsResponseDto(
        UUID id,
        UUID workspaceId,
        String name,
        String description,
        String isOwner,
        String ownerId,
        String ownerName
) {
}
