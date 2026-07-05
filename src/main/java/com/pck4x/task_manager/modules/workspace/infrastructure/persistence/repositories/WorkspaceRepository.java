package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import com.pck4x.task_manager.modules.workspace.infrastructure.mapper.WorkspaceMapper;
import com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa.JpaWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.CheckWorkspaceInvitationResponse;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDetailDto;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDto;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WorkspaceRepository implements IWorkspaceRepository {
    private final WorkspaceMapper workspaceMapper;
    private final JpaWorkspaceRepository jpa;

    @Override
    @Transactional
    public TWorkspace create(TWorkspace workspace) {
        WorkspaceEntity entity = workspaceMapper.toEntity(workspace);

        WorkspaceEntity saved = jpa.save(entity);

        return workspaceMapper.toDomain(saved);
    }

    @Override
    public void delete(TWorkspace workspace) {
        jpa.delete(workspaceMapper.toEntity(workspace));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TWorkspace> getWorkspace(UUID id) {
        return jpa.findById(id)
                .map(workspaceMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkspaceDetailDto> getWorkspaceByIdAndOwnerId(
            UUID workspaceId,
            UUID userId
    ) {
        return jpa.findWorkspaceDetailBase(workspaceId, userId)
                .map(base -> new WorkspaceDetailDto(
                        base.id(),
                        base.name(),
                        base.description(),
                        base.isPrivate(),
                        base.isOwner(),
                        base.ownerId(),
                        base.ownerName(),
                        jpa.findMembers(workspaceId, userId),
                        jpa.findChannels(workspaceId),
                        jpa.findBoards(workspaceId),
                        base.createdAt()
                ));
    }

    @Override
    @Transactional(readOnly = true)
    public QueryResult<List<WorkspaceDto>> getAllWorkspaceByOwnerId(UUID ownerId, Pageable pageable) {
        int pageIndex = Math.max(pageable.getPageNumber(), 0);
        Pageable pageRequest = PageRequest.of(pageIndex, pageable.getPageSize());

        Page<WorkspaceDto> result =
                jpa.findAllByOwnerOrMember(ownerId, pageRequest);

        return QueryResult.success(
                result.getContent(),
                (int) result.getTotalElements(),
                result.getTotalPages(),
                result.getNumber(),
                result.getSize()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public QueryResult<List<CheckWorkspaceInvitationResponse>> findInvitableUsers(UUID workspaceId, String query, Pageable pageable) {
        int pageIndex = Math.max(pageable.getPageNumber(), 0);
        Pageable pageRequest = PageRequest.of(pageIndex, pageable.getPageSize());

        Page<CheckWorkspaceInvitationResponse> result =
                jpa.findInvitableUsers(workspaceId, query, WorkspaceInvitationStatus.PENDING, pageRequest);

        return QueryResult.success(
                result.getContent(),
                (int) result.getTotalElements(),
                result.getTotalPages(),
                result.getNumber(),
                result.getSize()
        );
    }
}
