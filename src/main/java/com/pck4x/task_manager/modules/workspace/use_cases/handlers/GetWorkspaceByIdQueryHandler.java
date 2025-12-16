package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDetailDto;
import com.pck4x.task_manager.modules.workspace.use_cases.query.GetWorkspacesByIdQuery;
import com.pck4x.task_manager.shared.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetWorkspaceByIdQueryHandler implements GetWorkspacesByIdQuery {
    private final IWorkspaceRepository workspaceRepository;

    @Override
    public Result<Optional<WorkspaceDetailDto>> execute(UUID id, UUID userId) {
        var workspace = workspaceRepository.getWorkspaceByIdAndOwnerId(id, userId);
        if (workspace.isEmpty()) return Result.notFound("Workspace not found");

        return Result.success(workspace,"Workspace is ready");
    }
}
