package com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response;

import java.util.UUID;

public record GetReceivedInvitationsResponse(
        UUID id,
        String invitedByName,
        String workspaceId,
        String workspaceName,
        String status
) {
}
