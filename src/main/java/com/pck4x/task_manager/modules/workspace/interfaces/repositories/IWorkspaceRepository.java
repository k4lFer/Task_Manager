package com.pck4x.task_manager.modules.workspace.interfaces.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDetailDto;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWorkspaceRepository {
    TWorkspace create(TWorkspace workspace);
    Optional<TWorkspace> getWorkspace(UUID id);
    Optional<WorkspaceDetailDto> getWorkspaceByIdAndOwnerId(UUID id, UUID ownerId);
    QueryResult<List<WorkspaceDto>> getAllWorkspaceByOwnerId(UUID ownerId, Pageable pageable);
}
