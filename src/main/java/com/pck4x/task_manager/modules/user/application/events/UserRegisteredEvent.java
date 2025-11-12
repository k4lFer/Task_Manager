package com.pck4x.task_manager.modules.user.application.events;

import org.springframework.modulith.events.Externalized;

import java.util.UUID;

@Externalized
public record UserRegisteredEvent(UUID id, String username, String password) {}

