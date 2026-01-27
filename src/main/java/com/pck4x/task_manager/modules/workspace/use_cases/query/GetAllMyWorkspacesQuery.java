package com.pck4x.task_manager.modules.workspace.use_cases.query;

import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import com.pck4x.task_manager.shared.result.OutputPort;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface GetAllMyWorkspacesQuery {
    OutputPort<QueryResult<List<WorkspaceDto>>> execute(UUID id, Pageable pageable);
}
