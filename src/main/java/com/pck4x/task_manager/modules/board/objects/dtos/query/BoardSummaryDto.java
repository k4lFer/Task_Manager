package com.pck4x.task_manager.modules.board.objects.dtos.query;

import java.util.UUID;

public record BoardSummaryDto(
        UUID id,
        UUID workspaceId,
        String name,
        String description,
        Boolean isOwner,
        UUID ownerId,
        String ownerName,
        int membersCount,
        int listsCount,
        int cardsCount
) {
}
