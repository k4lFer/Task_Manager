package com.pck4x.task_manager.modules.workspace.interfaces.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceInvitation;

import java.util.UUID;

public interface IWorkspaceInvitationRepository {
    TWorkspaceInvitation save(TWorkspaceInvitation tWorkspaceInvitation);
    Boolean existsPending(UUID workspaceId, UUID memberId, String email);
    Boolean existsPendingByEmail(UUID workspaceId, String email);
    TWorkspaceInvitation findById(UUID id);
}
