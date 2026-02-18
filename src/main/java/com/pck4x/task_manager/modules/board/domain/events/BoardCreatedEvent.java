package com.pck4x.task_manager.modules.board.domain.events;

import org.jmolecules.event.annotation.DomainEvent;

import java.util.UUID;

@DomainEvent
public record BoardCreatedEvent(
        UUID id
) {
}
