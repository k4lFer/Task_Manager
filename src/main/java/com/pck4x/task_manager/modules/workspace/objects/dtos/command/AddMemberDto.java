package com.pck4x.task_manager.modules.workspace.objects.dtos.command;

import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceMemberRole;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AddMemberDto {
    public UUID workspaceId;
    public UUID memberId;
    public WorkspaceMemberRole role;
}
