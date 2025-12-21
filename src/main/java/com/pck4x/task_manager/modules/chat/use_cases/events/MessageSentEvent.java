package com.pck4x.task_manager.modules.chat.use_cases.events;

import org.jmolecules.event.annotation.Externalized;

import java.time.Instant;
import java.util.UUID;

@Externalized
public record MessageSentEvent(
        UUID channelId,
        String message,
        UUID senderId,
        Instant sentAt) {
}
