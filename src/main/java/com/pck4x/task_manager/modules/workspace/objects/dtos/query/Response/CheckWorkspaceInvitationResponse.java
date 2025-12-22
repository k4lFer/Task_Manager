package com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CheckWorkspaceInvitationResponse {
    private boolean canInvite;
    private boolean alreadyMember;
    private boolean pendingInvitation;
    private UUID id;
    private String email;
    private String fullName;
    private String reason;
}
