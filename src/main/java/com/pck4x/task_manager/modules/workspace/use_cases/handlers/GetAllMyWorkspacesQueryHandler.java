package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDto;
import com.pck4x.task_manager.modules.workspace.use_cases.query.GetAllMyWorkspacesQuery;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetAllMyWorkspacesQueryHandler implements GetAllMyWorkspacesQuery {
    private final IWorkspaceRepository workspaceRepository;

    @Override
    public OutputPort<QueryResult<List<WorkspaceDto>>> execute(UUID id, Pageable pageable) {
        var result = workspaceRepository.getAllWorkspaceByOwnerId(id, pageable);

        if (result.getResults().isEmpty()) return OutputPort.failure(HttpStatus.NO_CONTENT, null);

        return OutputPort.success(result, HttpStatus.OK, "Your workspaces are ready");
    }
}
