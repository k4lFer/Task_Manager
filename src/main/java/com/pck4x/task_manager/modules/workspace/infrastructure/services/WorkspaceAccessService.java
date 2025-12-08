package com.pck4x.task_manager.modules.workspace.infrastructure.services;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceMemberRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceMemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkspaceAccessService implements IWorkspaceAccessService {

    private final IWorkspaceRepository workspaceRepository;
    private final IWorkspaceMemberRepository workspaceMemberRepository;

    @Override
    public boolean isOwner(UUID workspaceId, UUID userId) {
        return workspaceRepository.getWorkspace(workspaceId)
                .map(w -> w.getOwnerId().equals(userId))
                .orElse(false);
    }

    @Override
    public boolean hasRole(UUID workspaceId, UUID userId, WorkspaceMemberRole role) {
        return workspaceMemberRepository.findByWorkspaceIdAndMemberId(workspaceId, userId)
                .map(m -> m.getRole() == role)
                .orElse(false);
    }

    @Override
    public boolean isAdminOrOwner(UUID workspaceId, UUID userId) {
        if (isOwner(workspaceId, userId)) {
            return true;
        }
        return hasRole(workspaceId, userId, WorkspaceMemberRole.ADMIN);
    }
}
