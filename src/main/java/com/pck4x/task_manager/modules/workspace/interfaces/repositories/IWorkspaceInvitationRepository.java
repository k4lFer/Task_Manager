package com.pck4x.task_manager.modules.workspace.interfaces.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceInvitation;

import java.util.Optional;
import java.util.UUID;

public interface IWorkspaceInvitationRepository {
    TWorkspaceInvitation save(TWorkspaceInvitation tWorkspaceInvitation);
    TWorkspaceInvitation patch();
    Boolean existsPending(UUID workspaceId, UUID memberId, String email);
    Boolean existsPendingByEmail(UUID workspaceId, String email);
    Optional<TWorkspaceInvitation> findById(UUID id);
}
