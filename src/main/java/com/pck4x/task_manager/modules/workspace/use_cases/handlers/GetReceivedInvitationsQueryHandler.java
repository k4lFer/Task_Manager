package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.GetReceivedInvitationsResponse;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import com.pck4x.task_manager.modules.workspace.use_cases.query.GetReceivedInvitationsQuery;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import com.pck4x.task_manager.shared.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class GetReceivedInvitationsQueryHandler implements GetReceivedInvitationsQuery {
    private final IWorkspaceInvitationRepository invitationRepository;

    @Override
    public Result<QueryResult<List<GetReceivedInvitationsResponse>>> execute(UUID userId, Pageable pageable, WorkspaceInvitationStatus status) {
        var result = invitationRepository.GetReceivedInvitations(userId, pageable, status);

        if (result.getResults().isEmpty()) return Result.noContent();

        return Result.success(result, "Your invitations are ready");
    }
}
