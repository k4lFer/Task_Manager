package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaWorkspaceMemberRepository extends JpaRepository<WorkspaceMemberEntity, UUID> {
    Optional<WorkspaceMemberEntity> findByWorkspaceIdAndMemberId(UUID workspaceId, UUID memberId);
}
