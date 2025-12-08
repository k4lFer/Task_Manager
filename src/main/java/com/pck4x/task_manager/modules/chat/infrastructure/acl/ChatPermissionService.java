package com.pck4x.task_manager.modules.chat.infrastructure.acl;

import com.pck4x.task_manager.modules.chat.interfaces.acl.IChatPermissionService;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatPermissionService implements IChatPermissionService {

    private final IWorkspaceAccessService workspaceAccessService;

    @Override
    public boolean canCreateChannel(UUID workspaceId, UUID memberId) {
        return workspaceAccessService.isAdminOrOwner(workspaceId, memberId);
    }
}
