package com.pck4x.task_manager.modules.workspace.use_cases.command;

import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface AcceptWorkspaceInvitationCommand {
    OutputPort<UUID> execute(UUID id, UUID invitationId);
}
