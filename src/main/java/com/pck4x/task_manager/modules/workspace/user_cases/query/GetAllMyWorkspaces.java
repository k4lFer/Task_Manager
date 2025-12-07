package com.pck4x.task_manager.modules.workspace.user_cases.query;

import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDetailDto;
import com.pck4x.task_manager.shared.result.Result;

import java.util.List;
import java.util.UUID;

public interface GetAllMyWorkspaces {
    Result<List<WorkspaceDetailDto>> execute(UUID id);
}
