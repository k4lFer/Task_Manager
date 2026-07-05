package com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Schema(description = "Result of checking whether a user can be invited to a workspace")
public class CheckWorkspaceInvitationResponse {
    @Schema(description = "Whether the user can be invited")
    private boolean canInvite;
    @Schema(description = "Whether the user is already a member")
    private boolean alreadyMember;
    @Schema(description = "Whether there is already a pending invitation")
    private boolean pendingInvitation;
    @Schema(description = "User ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;
    @Schema(description = "User email", example = "jane@example.com")
    private String email;
    @Schema(description = "User full name", example = "Jane Smith")
    private String fullName;
    @Schema(description = "Reason if the user cannot be invited", example = "Already a member")
    private String reason;
}
