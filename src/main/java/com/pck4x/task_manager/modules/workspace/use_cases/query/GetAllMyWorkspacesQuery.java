package com.pck4x.task_manager.modules.workspace.use_cases.query;

import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDetailDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import com.pck4x.task_manager.shared.objects.QueryDto;
import com.pck4x.task_manager.shared.result.Result;

import java.util.List;
import java.util.UUID;

public interface GetAllMyWorkspacesQuery {
    Result<QueryResult<List<WorkspaceDetailDto>>> execute(UUID id, QueryDto options);
}
