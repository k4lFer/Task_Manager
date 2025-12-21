package com.pck4x.task_manager.modules.workspace.infrastructure.entities;

import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationRole;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "workspace_invitations", schema = "workspace")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkspaceInvitationEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workspace_id", nullable = false)
    private WorkspaceEntity workspace;

    @Column(name = "invited_by", nullable = false)
    private UUID invitedBy;

    @Column(name = "invited_user_id", nullable = true)
    private UUID invitedUserId;

    @Column(name = "invited_email", nullable = true)
    private String invitedEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private WorkspaceInvitationRole role;

    @Column(name = "token")
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private WorkspaceInvitationStatus status;

    @Column(name = "expires_at", columnDefinition = "timestamptz")
    private Instant expiresAt;

    @Column(name = "responded_at", columnDefinition = "timestamptz")
    private Instant respondedAt;

    @Column(name = "created_at", columnDefinition = "timestamptz")
    private Instant createdAt;

}
