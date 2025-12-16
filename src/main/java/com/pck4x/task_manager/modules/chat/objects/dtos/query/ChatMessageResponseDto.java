package com.pck4x.task_manager.modules.chat.objects.dtos.query;

import java.time.Instant;
import java.util.UUID;

public record ChatMessageResponseDto(
        UUID id,
        UUID channelId,
        String message,
        UUID senderId,
        String senderName,
        Instant sentAt,
        Instant editedAt,
        boolean isEdited
) { }
