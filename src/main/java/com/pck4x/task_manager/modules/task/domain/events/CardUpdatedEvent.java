package com.pck4x.task_manager.modules.task.domain.events;

import org.jmolecules.event.annotation.DomainEvent;

import java.util.UUID;

@DomainEvent
public record CardUpdatedEvent(
        UUID id
) {
}
