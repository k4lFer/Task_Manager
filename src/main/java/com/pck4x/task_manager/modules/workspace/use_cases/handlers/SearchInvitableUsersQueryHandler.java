package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.CheckWorkspaceInvitationResponse;
import com.pck4x.task_manager.modules.workspace.use_cases.query.SearchInvitableUsersQuery;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SearchInvitableUsersQueryHandler implements SearchInvitableUsersQuery {
    private final IWorkspaceRepository workspaceRepository;

    @Override
    public OutputPort<QueryResult<List<CheckWorkspaceInvitationResponse>>> execute(UUID workspaceId, String query, Pageable pageable) {
        var result = workspaceRepository.findInvitableUsers(workspaceId, query, pageable);

        if (result.getResults().isEmpty()) return OutputPort.failure(HttpStatus.NOT_FOUND, "Users not found");

        return OutputPort.success(result, HttpStatus.OK, "Users are ready");
    }
}
