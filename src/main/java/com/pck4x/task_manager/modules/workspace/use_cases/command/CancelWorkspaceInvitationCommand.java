package com.pck4x.task_manager.modules.workspace.use_cases.command;

import com.pck4x.task_manager.shared.result.Result;

import java.util.UUID;

public interface CancelWorkspaceInvitationCommand {
    Result<?> execute(UUID id, UUID invitationId);
}
