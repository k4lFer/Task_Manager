package com.pck4x.task_manager.modules.chat.domain.models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TChatMessage {
    private UUID id;
    private UUID chatChannelId;
    private UUID userId;
    private String message;
    private JsonNode content;
    private Instant sentAt;
    private Instant editedAt;

    public static TChatMessage create(UUID chatChannelId, UUID userId, String message, JsonNode content) {
        return TChatMessage.builder()
                .id(UUID.randomUUID())
                .chatChannelId(chatChannelId)
                .userId(userId)
                .message(message)
                .content(content)
                .sentAt(Instant.now())
                .build();
    }

    public void edit(String newMessage) {
        this.message = newMessage;
        this.editedAt = Instant.now();
    }
}
