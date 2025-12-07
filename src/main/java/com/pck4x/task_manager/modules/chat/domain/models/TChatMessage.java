package com.pck4x.task_manager.modules.chat.domain.models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class TChatMessage {
    private UUID id;
    private UUID chatChannelId;
    private UUID userId;
    private String message;
    private JsonNode content;
    private Instant sentAt;
    private Instant editedAt;
}
