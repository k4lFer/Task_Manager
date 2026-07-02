package com.pck4x.task_manager.modules.workspace.interfaces.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;

import java.util.Optional;
import java.util.UUID;

public interface IWorkspaceMemberRepository {
    TWorkspaceMembers save(TWorkspaceMembers workspaceMembers);

    void deleteMember(UUID memberId);

    void deleteByWorkspaceIdAndMemberId(UUID workspaceId, UUID memberId);

    Optional<TWorkspaceMembers> findByWorkspaceIdAndMemberId(UUID workspaceId, UUID memberId);
}
