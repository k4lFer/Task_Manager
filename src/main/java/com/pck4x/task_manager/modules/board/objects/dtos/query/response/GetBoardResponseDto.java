package com.pck4x.task_manager.modules.board.objects.dtos.query.response;

import java.util.List;
import java.util.UUID;

public record GetBoardResponseDto(
        UUID id,
        UUID workspaceId,
        UUID ownerId,
        String ownerName,
        String name,
        String description,
        List<Object> members,
        List<Object> lists,
        List<Object> labels
) {
}
