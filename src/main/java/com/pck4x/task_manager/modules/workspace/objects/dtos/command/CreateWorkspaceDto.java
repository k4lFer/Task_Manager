package com.pck4x.task_manager.modules.workspace.objects.dtos.command;

import lombok.Getter;

@Getter
public class CreateWorkspaceDto {
    public String name;
    public String description;
    public Boolean isPrivate;
}
