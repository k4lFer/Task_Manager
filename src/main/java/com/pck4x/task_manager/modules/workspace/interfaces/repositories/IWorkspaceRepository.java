package com.pck4x.task_manager.modules.workspace.interfaces.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDetailDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import com.pck4x.task_manager.shared.objects.QueryDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWorkspaceRepository {
    TWorkspace create(TWorkspace workspace);
    Optional<WorkspaceDetailDto> getWorkspace(UUID id);
    QueryResult<List<WorkspaceDetailDto>> getAllWorkspaceByOwnerId(UUID ownerId, QueryDto options);
}
