package com.pck4x.task_manager.modules.board.domain.events;

import org.jmolecules.event.annotation.DomainEvent;

import java.util.List;
import java.util.UUID;

@DomainEvent
public record BoardDeletedEvent(
        UUID id,
        List<UUID> listIds
) {
}
