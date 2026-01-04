package com.pck4x.task_manager.modules.workspace.controllers;

import com.pck4x.task_manager.modules.workspace.objects.dtos.command.CreateWorkspaceDto;
import com.pck4x.task_manager.modules.workspace.objects.dtos.command.SendWorkspaceInvitationDto;
import com.pck4x.task_manager.modules.workspace.use_cases.command.*;
import com.pck4x.task_manager.shared.helper.ResponseHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/workspace")
@Tag(name = "Workspace")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class WorkspaceCommandController {
    private final SendWorkspaceInvitationCommand sendWorkspaceInvitationCommand;
    private final CreateWorkspaceCommand createWorkspaceCommand;
    private final AcceptWorkspaceInvitationCommand acceptWorkspaceInvitationCommand;
    private final RejectWorkspaceInvitationCommand rejectWorkspaceInvitationCommand;
    private final CancelWorkspaceInvitationCommand cancelWorkspaceInvitationCommand;

    @PostMapping("/create")
    @Operation(
            summary = "Create a new workspace",
            description = "Creates a new workspace where the authenticated user becomes the owner. The workspace can be public or private based on the provided configuration."
    )
    public ResponseEntity<?> Create(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @RequestBody CreateWorkspaceDto input
    ) {
        var result = createWorkspaceCommand.execute(UUID.fromString(userId), input);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/send-invitation")
    @Operation(
            summary = "Send workspace invitation",
            description = "Sends an invitation to a user to join a workspace. Only the workspace owner or administrators can send invitations. Only the owner can invite users with ADMIN role."
    )
    public ResponseEntity<?> SendInvitation(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @RequestBody SendWorkspaceInvitationDto input
        ){
        var result = sendWorkspaceInvitationCommand.execute(UUID.fromString(userId), input);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/invitations/{invitationId}/accept")
    @Operation(
            summary = "Accept workspace invitation",
            description = "Accepts a pending workspace invitation. The authenticated user becomes a member of the workspace with the role defined in the invitation."
    )
    public ResponseEntity<?> AcceptInvitation(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID invitationId
    ) {
        var result = acceptWorkspaceInvitationCommand.execute(UUID.fromString(userId), invitationId);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/invitations/{invitationId}/reject")
    @Operation(
            summary = "Reject workspace invitation",
            description = "Rejects a pending workspace invitation. The invitation will be marked as rejected and cannot be used again."
    )
    public ResponseEntity<?> RejectInvitation(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID invitationId
    ) {
        var result = rejectWorkspaceInvitationCommand.execute(UUID.fromString(userId), invitationId);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/invitations/{invitationId}/cancel")
    @Operation(
            summary = "Cancel sent workspace invitation",
            description = "Cancels a workspace invitation that was previously sent. Only the workspace owner or administrators can cancel an invitation."
    )
    public ResponseEntity<?> CancelInvitation(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID invitationId
    ) {
        var result = cancelWorkspaceInvitationCommand.execute(UUID.fromString(userId), invitationId);
        return ResponseHelper.toResponse(result);
    }

    @PostMapping("/{workspaceId}/members/{memberId}/remove")
    @Operation(
            summary = "Remove workspace member",
            description = "Removes a member from the workspace. Only the workspace owner or administrators can remove members. Owners cannot be removed."
    )
    public ResponseEntity<?> RemoveMember(
            @Parameter(hidden = true) @AuthenticationPrincipal String userId,
            @PathVariable UUID workspaceId,
            @PathVariable UUID memberId
    ) {
        return ResponseEntity.ok().build();
    }

}
