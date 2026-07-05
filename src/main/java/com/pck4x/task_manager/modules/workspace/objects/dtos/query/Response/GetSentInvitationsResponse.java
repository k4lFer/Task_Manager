package com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response;

import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Workspace invitation sent by the authenticated user")
public record GetSentInvitationsResponse(
        @Schema(description = "Invitation unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "Name of the invited user", example = "Jane Smith")
        String invitedName,
        @Schema(description = "Workspace ID", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID workspaceId,
        @Schema(description = "Workspace name", example = "My Project")
        String workspaceName,
        @Schema(description = "Current status of the invitation")
        WorkspaceInvitationStatus status
) {
}
