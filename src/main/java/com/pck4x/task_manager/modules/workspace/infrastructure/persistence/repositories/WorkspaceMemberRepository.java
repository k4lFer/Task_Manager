package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;
import com.pck4x.task_manager.modules.workspace.infrastructure.mapper.WorkspaceMemberMapper;
import com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa.JpaWorkspaceMemberRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WorkspaceMemberRepository implements IWorkspaceMemberRepository {

    private final JpaWorkspaceMemberRepository jpaWorkspaceMemberRepository;
    private final WorkspaceMemberMapper workspaceMemberMapper;

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

    @Override
    public Optional<TWorkspaceMembers> findByWorkspaceIdAndMemberId(UUID workspaceId, UUID memberId) {
        return jpaWorkspaceMemberRepository.findByWorkspaceIdAndMemberId(workspaceId, memberId)
                .map(workspaceMemberMapper::toDomain);
    }
}
