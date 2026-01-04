package com.pck4x.task_manager.modules.workspace.use_cases.query;

import com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.GetReceivedInvitationsResponse;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import com.pck4x.task_manager.shared.result.Result;

import java.util.List;
import java.util.UUID;

public interface GetReceivedInvitationsQuery {
    Result<QueryResult<List<GetReceivedInvitationsResponse>>> execute(UUID userId);
}
