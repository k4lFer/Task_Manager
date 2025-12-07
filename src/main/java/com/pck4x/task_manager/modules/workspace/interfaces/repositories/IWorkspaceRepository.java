package com.pck4x.task_manager.modules.workspace.interfaces.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDetailDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWorkspaceRepository {
    TWorkspace create(TWorkspace workspace);
    Optional<WorkspaceDetailDto> getWorkspace(UUID id);
    List<WorkspaceDetailDto> getAllWorkspaceByOwnerId(UUID ownerId);
}
