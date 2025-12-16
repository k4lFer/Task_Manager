package com.pck4x.task_manager.modules.chat.objects.dtos.query;

import java.util.UUID;

public record WorkspaceChannelDto (
        UUID channelId,
        String name,
        String description
) {
}
