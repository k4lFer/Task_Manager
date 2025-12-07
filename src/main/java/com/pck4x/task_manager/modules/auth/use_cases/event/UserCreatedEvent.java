package com.pck4x.task_manager.modules.auth.use_cases.event;

import org.springframework.modulith.events.Externalized;

import java.util.UUID;

@Externalized
public record UserCreatedEvent(UUID id) {
}
