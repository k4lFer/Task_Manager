package com.pck4x.task_manager.modules.workspace.use_cases.query;

import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDetailDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.Optional;
import java.util.UUID;

public interface GetWorkspacesByIdQuery {
    OutputPort<Optional<WorkspaceDetailDto>> execute(UUID id, UUID userId);
}
