package com.pck4x.task_manager.modules.workspace.objects.dtos.query;

import lombok.Data;

import java.util.UUID;

public record WorkspaceChannelDto(
        UUID id,
        String name,
        String description
) {}

