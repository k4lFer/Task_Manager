package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;
import com.pck4x.task_manager.modules.workspace.infrastructure.mapper.WorkspaceMemberMapper;
import com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa.JpaWorkspaceMemberRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WorkspaceMemberRepository implements IWorkspaceMemberRepository {

    private final JpaWorkspaceMemberRepository jpaWorkspaceMemberRepository;
    private final WorkspaceMemberMapper workspaceMemberMapper;

    @Override
    @Transactional
    public TWorkspaceMembers save(TWorkspaceMembers workspaceMembers) {
        var entity = workspaceMemberMapper.toEntity(workspaceMembers);
        var saved = jpaWorkspaceMemberRepository.save(entity);
        return workspaceMemberMapper.toDomain(saved);
    }

    @Override
    public void deleteMember(UUID memberId) {

    }

    @Override
    @Transactional
    public void deleteByWorkspaceIdAndMemberId(UUID workspaceId, UUID memberId) {
        jpaWorkspaceMemberRepository.deleteByWorkspaceIdAndMemberId(workspaceId, memberId);
    }

    @Override
    public Optional<TWorkspaceMembers> findByWorkspaceIdAndMemberId(UUID workspaceId, UUID memberId) {
        return jpaWorkspaceMemberRepository.findByWorkspaceIdAndMemberId(workspaceId, memberId)
                .map(workspaceMemberMapper::toDomain);
    }
}
