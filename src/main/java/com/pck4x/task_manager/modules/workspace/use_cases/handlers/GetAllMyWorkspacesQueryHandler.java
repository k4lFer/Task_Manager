package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDto;
import com.pck4x.task_manager.modules.workspace.use_cases.query.GetAllMyWorkspacesQuery;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import com.pck4x.task_manager.shared.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetAllMyWorkspacesQueryHandler implements GetAllMyWorkspacesQuery {
    private final IWorkspaceRepository workspaceRepository;

    @Override
    public Result<QueryResult<List<WorkspaceDto>>> execute(UUID id, Pageable pageable) {
        var result = workspaceRepository.getAllWorkspaceByOwnerId(id, pageable);

        if (result.getResults().isEmpty()) return Result.noContent();

        return Result.success(result, "Your workspaces are ready");
    }
}
