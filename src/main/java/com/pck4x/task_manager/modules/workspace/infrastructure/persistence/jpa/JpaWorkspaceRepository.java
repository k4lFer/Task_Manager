package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface JpaWorkspaceRepository extends JpaRepository<WorkspaceEntity, UUID> {
    @EntityGraph(attributePaths = {
            "owner.person",
            "workspaceMember.member.person",
    })
    Optional<WorkspaceEntity> findWithDetailsById(UUID id);

    @EntityGraph(attributePaths = {
            "owner.person",
            "workspaceMember.member.person"
    })
    @Query("SELECT DISTINCT w FROM WorkspaceEntity w " +
            "LEFT JOIN w.workspaceMember wm " +
            "WHERE w.owner.id = :userId OR wm.member.id = :userId")
    Page<WorkspaceEntity> findAllByOwnerOrMember(@Param("userId") UUID userId, Pageable pageable);
}
