package com.pck4x.task_manager.modules.workspace.objects.dtos.command;

import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationRole;
import lombok.Getter;

import java.util.UUID;

@Getter
public class SendWorkspaceInvitationDto {
    public UUID workspaceId;
    public String email;
    public WorkspaceInvitationRole role;
}
