package com.pck4x.task_manager.modules.chat.use_cases.events;

import java.time.Instant;
import java.util.UUID;

public record MessageSentEvent(
        UUID channelId,
        String message,
        UUID senderId,
        Instant sentAt) {
}
