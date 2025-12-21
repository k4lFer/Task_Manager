package com.pck4x.task_manager.modules.chat.use_cases.events;

import org.jmolecules.event.annotation.Externalized;

import java.time.Instant;
import java.util.UUID;

@Externalized
public record MessageEditedEvent(
        UUID messageId,
        UUID channelId,
        String newMessage,
        UUID editorId,
        Instant editedAt) {
}
