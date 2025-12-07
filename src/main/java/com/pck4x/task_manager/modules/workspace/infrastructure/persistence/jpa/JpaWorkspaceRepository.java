package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaWorkspaceRepository extends JpaRepository<WorkspaceEntity, UUID> {
    @EntityGraph(attributePaths = {
            "owner.person",
            "workspaceMember.member.person"
    })
    @Override
    Optional<WorkspaceEntity> findById(UUID id);

    @EntityGraph(attributePaths = {
            "owner.person",
            "workspaceMember.member.person" // Opcional si en la lista no muestras miembros
    })
    List<WorkspaceEntity> findAllByOwnerId(UUID ownerId);
}
