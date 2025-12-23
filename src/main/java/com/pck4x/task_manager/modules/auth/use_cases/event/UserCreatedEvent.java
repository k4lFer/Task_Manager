package com.pck4x.task_manager.modules.auth.use_cases.event;

import org.springframework.modulith.events.Externalized;

import java.time.Instant;
import java.util.UUID;

@Externalized
public record UserCreatedEvent(
        UUID id,
        Instant createdAt) {
}
