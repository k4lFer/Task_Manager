package com.pck4x.task_manager.modules.chat.use_cases.events;

import java.time.Instant;
import java.util.UUID;

public record MessageEditedEvent(
        UUID messageId,
        UUID channelId,
        String newMessage,
        UUID editorId,
        Instant editedAt) {
}
