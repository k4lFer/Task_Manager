package com.pck4x.task_manager.modules.workspace.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import com.pck4x.task_manager.modules.workspace.infrastructure.mapper.WorkspaceMapper;
import com.pck4x.task_manager.modules.workspace.infrastructure.mapper.WorkspaceQueryMapper;
import com.pck4x.task_manager.modules.workspace.infrastructure.persistence.jpa.JpaWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WorkspaceRepository implements IWorkspaceRepository {
    private final WorkspaceMapper workspaceMapper;
    private final WorkspaceQueryMapper queryMapper;
    private final JpaWorkspaceRepository jpa;

    @Override
    @Transactional
    public TWorkspace create(TWorkspace workspace) {
        WorkspaceEntity entity = workspaceMapper.toEntity(workspace);

        WorkspaceEntity saved = jpa.save(entity);

        return workspaceMapper.toDomain(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkspaceDetailDto> getWorkspace(UUID id) {
        return jpa.findById(id)
                .map(queryMapper::toDetailDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkspaceDetailDto> getAllWorkspaceByOwnerId(UUID ownerId) {
        return jpa.findAllByOwnerId(ownerId)
                .stream()
                .map(queryMapper::toDetailDto)
                .toList();
    }
}
