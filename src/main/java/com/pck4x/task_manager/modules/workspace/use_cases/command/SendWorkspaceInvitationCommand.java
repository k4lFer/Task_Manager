package com.pck4x.task_manager.modules.workspace.use_cases.command;

import com.pck4x.task_manager.modules.workspace.objects.dtos.command.SendWorkspaceInvitationDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface SendWorkspaceInvitationCommand {
    OutputPort<UUID> execute(UUID invitedBy, SendWorkspaceInvitationDto input);
}
