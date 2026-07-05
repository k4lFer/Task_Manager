package com.pck4x.task_manager.modules.chat.objects.dtos.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Chat channel information")
public record WorkspaceChannelDto (
        @Schema(description = "Channel unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID channelId,
        @Schema(description = "Channel name", example = "general")
        String name,
        @Schema(description = "Channel description", example = "General discussion channel")
        String description
) {
}
