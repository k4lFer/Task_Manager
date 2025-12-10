package com.pck4x.task_manager.modules.workspace.interfaces.services;

import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceMemberRole;
import java.util.UUID;

public interface IWorkspaceAccessService {
    boolean isOwner(UUID workspaceId, UUID userId);
    boolean isAdmin(UUID workspaceId, UUID userId);
    boolean isMember(UUID workspaceId, UUID userId);
    boolean isGuest(UUID workspaceId, UUID userId);

    boolean hasRole(UUID workspaceId, UUID userId, WorkspaceMemberRole role);

    /**
     * Checks if the user is either the Owner OR has the ADMIN role.
     */
    boolean isAdminOrOwner(UUID workspaceId, UUID userId);
}
