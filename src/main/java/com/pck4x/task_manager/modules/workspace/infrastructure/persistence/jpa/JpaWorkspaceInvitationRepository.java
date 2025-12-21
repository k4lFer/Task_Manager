package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceInvitationEntity;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaWorkspaceInvitationRepository extends JpaRepository<WorkspaceInvitationEntity, UUID> {
    boolean existsByWorkspaceIdAndInvitedUserIdAndStatus(
            UUID workspaceId,
            UUID invitedUserId,
            WorkspaceInvitationStatus status
    );

    boolean existsByWorkspaceIdAndInvitedEmailAndStatus(
            UUID workspaceId,
            String invitedEmail,
            WorkspaceInvitationStatus status
    );
}
