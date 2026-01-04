package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceInvitation;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceInvitationEntity;
import com.pck4x.task_manager.modules.workspace.infrastructure.mapper.WorkspaceInvitationMapper;
import com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa.JpaWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa.JpaWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.GetReceivedInvitationsResponse;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.GetSentInvitationsResponse;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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
    public TWorkspaceInvitation updateStatus(TWorkspaceInvitation tWorkspaceInvitation) {
        WorkspaceInvitationEntity entity = jpa.findById(tWorkspaceInvitation.getId())
                .orElseThrow(() -> new IllegalStateException("Invitation not found"));

        entity.setStatus(tWorkspaceInvitation.getStatus());
        entity.setRespondedAt(tWorkspaceInvitation.getRespondedAt());

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
    public Optional<TWorkspaceInvitation> findById(UUID id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public QueryResult<List<GetReceivedInvitationsResponse>> GetReceivedInvitations(UUID userId, Pageable pageable, WorkspaceInvitationStatus status) {
        int pageIndex = Math.max(pageable.getPageNumber(), 0);
        Pageable pageRequest = PageRequest.of(pageIndex, pageable.getPageSize());

        if (status == null)
            status = WorkspaceInvitationStatus.PENDING;

        Page<GetReceivedInvitationsResponse> result = jpa.getReceivedInvitations(userId, status, pageRequest);

        return QueryResult.success(
                result.getContent(),
                (int) result.getTotalElements(),
                result.getTotalPages(),
                result.getNumber(),
                result.getSize()
        );
    }

    @Override
    public QueryResult<List<GetSentInvitationsResponse>> GetSentInvitations(UUID userId, Pageable pageable, WorkspaceInvitationStatus status) {
        int pageIndex = Math.max(pageable.getPageNumber(), 0);
        Pageable pageRequest = PageRequest.of(pageIndex, pageable.getPageSize());

        if (status == null)
            status = WorkspaceInvitationStatus.PENDING;

        Page<GetSentInvitationsResponse> result = jpa.getSentInvitations(userId, status, pageRequest);

        return QueryResult.success(
                result.getContent(),
                (int) result.getTotalElements(),
                result.getTotalPages(),
                result.getNumber(),
                result.getSize()
        );
    }
}
