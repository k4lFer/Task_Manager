package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceInvitation;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceInvitationEntity;
import com.pck4x.task_manager.modules.workspace.infrastructure.mapper.WorkspaceInvitationMapper;
import com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa.JpaWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa.JpaWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WorkspaceInvitationRepository implements IWorkspaceInvitationRepository {
    private final JpaWorkspaceInvitationRepository jpa;
    private final JpaWorkspaceRepository jpaWorkspaceRepository;
    private final WorkspaceInvitationMapper mapper;

    @Override
    public TWorkspaceInvitation save(TWorkspaceInvitation tWorkspaceInvitation) {
        var workspace = jpaWorkspaceRepository.findById(tWorkspaceInvitation.getWorkspaceId());

        WorkspaceInvitationEntity entity = mapper.toEntity(tWorkspaceInvitation);

        if (workspace.isEmpty()) return null;

        entity.setWorkspace(workspace.get());

        WorkspaceInvitationEntity saved = jpa.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Boolean existsPending(UUID workspaceId, UUID memberId, String email) {
        if (memberId != null) {
            return jpa.existsByWorkspaceIdAndInvitedUserIdAndStatus(
                    workspaceId,
                    memberId,
                    WorkspaceInvitationStatus.PENDING
            );
        }

        return jpa.existsByWorkspaceIdAndInvitedEmailAndStatus(
                workspaceId,
                email,
                WorkspaceInvitationStatus.PENDING
        );
    }

    @Override
    public Boolean existsPendingByEmail(UUID workspaceId, String email) {
        return jpa.existsByWorkspaceIdAndInvitedEmailAndStatus(
                workspaceId,
                email,
                WorkspaceInvitationStatus.PENDING
        );
    }

    @Override
    public TWorkspaceInvitation findById(UUID id) {
        return null;
    }
}
