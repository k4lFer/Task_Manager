package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WorkspaceMemberRepository implements IWorkspaceMemberRepository {
    @Override
    public TWorkspaceMembers save(TWorkspaceMembers workspaceMembers) {
        return null;
    }

    @Override
    public void deleteMember(UUID memberId) {

    }

    @Override
    public void addMember(UUID memberId) {

    }
}
