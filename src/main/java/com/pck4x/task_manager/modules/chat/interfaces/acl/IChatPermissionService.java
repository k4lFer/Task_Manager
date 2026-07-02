package com.pck4x.task_manager.modules.chat.interfaces.acl;

import java.util.UUID;

public interface IChatPermissionService {
    boolean canCreateChannel(UUID workspaceId, UUID memberId);
    boolean canDeleteChannel(UUID workspaceId, UUID memberId);
}
