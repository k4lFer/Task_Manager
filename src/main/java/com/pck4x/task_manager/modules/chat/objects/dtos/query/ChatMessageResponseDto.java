package com.pck4x.task_manager.modules.chat.objects.dtos.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Chat message in a channel")
public record ChatMessageResponseDto(
        @Schema(description = "Message unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "Channel ID", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID channelId,
        @Schema(description = "Message content", example = "Hello everyone!")
        String message,
        @Schema(description = "Sender's user ID", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID senderId,
        @Schema(description = "Sender's display name", example = "John Doe")
        String senderName,
        @Schema(description = "When the message was sent", example = "2024-01-15T10:30:00Z")
        Instant sentAt,
        @Schema(description = "When the message was last edited", example = "2024-01-15T11:00:00Z")
        Instant editedAt,
        @Schema(description = "Whether the message has been edited")
        boolean isEdited
) { }
