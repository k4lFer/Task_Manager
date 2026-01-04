package com.pck4x.task_manager.modules.workspace.domain.models;

import com.pck4x.task_manager.modules.workspace.domain.events.WorkspaceInvitationAcceptedEvent;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationRole;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import com.pck4x.task_manager.shared.domain.repository.TGenericDomain;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Getter
@Builder
public class TWorkspaceInvitation extends TGenericDomain {
    private UUID id;
    private UUID workspaceId;
    private UUID invitedBy;
    private UUID invitedUserId;
    private String invitedEmail;
    private WorkspaceInvitationRole role;
    private String token;
    private WorkspaceInvitationStatus status;
    private Instant expiresAt;
    private Instant respondedAt;
    private Instant createdAt;

    public static TWorkspaceInvitation create(UUID workspaceId, UUID invitedBy, UUID invitedUserId, String invitedEmail, WorkspaceInvitationRole role) {
        return TWorkspaceInvitation.builder()
                .id(UUID.randomUUID())
                .workspaceId(workspaceId)
                .invitedBy(invitedBy)
                .invitedUserId(invitedUserId)
                .invitedEmail(invitedEmail)
                .role(role)
                .token(UUID.randomUUID().toString())
                .status(WorkspaceInvitationStatus.PENDING)
                .expiresAt(Instant.now().plus(48, ChronoUnit.HOURS))
                .respondedAt(null)
                .createdAt(Instant.now())
                .build();
    }

    public void accept() {
        this.status = WorkspaceInvitationStatus.ACCEPTED;
        this.respondedAt = Instant.now();

        domainEvents.add(new WorkspaceInvitationAcceptedEvent(this.workspaceId, this.invitedUserId, this.role, this.respondedAt));
    }

    public void expired() {
        this.status = WorkspaceInvitationStatus.EXPIRED;
    }

    public void rejected() {
        this.status = WorkspaceInvitationStatus.REJECTED;
        this.respondedAt = Instant.now();
    }

    public void cancel() {
        this.status = WorkspaceInvitationStatus.CANCELED;
    }
}
