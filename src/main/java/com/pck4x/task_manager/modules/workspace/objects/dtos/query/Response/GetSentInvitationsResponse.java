package com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response;

import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;

import java.util.UUID;

public record GetSentInvitationsResponse(
        UUID id,
        String invitedName,
        UUID workspaceId,
        String workspaceName,
        WorkspaceInvitationStatus status
) {
}
