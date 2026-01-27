package com.pck4x.task_manager.modules.workspace.use_cases.command;

import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface DeleteWorkspaceCommand {
    OutputPort<Void> execute(UUID userId, UUID workspaceId);
}
