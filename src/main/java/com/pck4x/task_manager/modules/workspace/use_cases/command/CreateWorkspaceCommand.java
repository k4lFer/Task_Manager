package com.pck4x.task_manager.modules.workspace.use_cases.command;

import com.pck4x.task_manager.modules.workspace.objects.dtos.command.CreateWorkspaceDto;
import com.pck4x.task_manager.shared.result.Result;

import java.util.UUID;

public interface CreateWorkspaceCommand {
    Result<UUID> execute(UUID userId, CreateWorkspaceDto input);
}
