package com.pck4x.task_manager.modules.workspace.objects.dtos.query;

import lombok.Data;

import java.util.UUID;

@Data
public class WorkspaceChannelDto {
    private UUID id;
    private String name;
    private String description;
}
