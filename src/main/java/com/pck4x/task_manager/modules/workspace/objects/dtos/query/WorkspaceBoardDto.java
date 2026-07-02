package com.pck4x.task_manager.modules.workspace.objects.dtos.query;

import java.time.Instant;
import java.util.UUID;

public record WorkspaceBoardDto(
        UUID id,
        String name,
        String description,
        UUID ownerId,
        String ownerName,
        int membersCount,
        int listsCount,
        int cardsCount,
        Instant createdAt
) {

}
