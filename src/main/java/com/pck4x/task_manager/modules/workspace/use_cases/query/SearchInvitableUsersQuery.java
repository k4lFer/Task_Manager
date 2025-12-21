package com.pck4x.task_manager.modules.workspace.use_cases.query;

import com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.CheckWorkspaceInvitationResponse;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import com.pck4x.task_manager.shared.result.Result;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SearchInvitableUsersQuery {
    Result<QueryResult<List<CheckWorkspaceInvitationResponse>>> execute(UUID workspaceId, String query, Pageable pageable);
}
