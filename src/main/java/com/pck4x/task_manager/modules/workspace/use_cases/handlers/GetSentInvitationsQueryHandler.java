package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.GetSentInvitationsResponse;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import com.pck4x.task_manager.modules.workspace.use_cases.query.GetSentInvitationsQuery;
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
public class GetSentInvitationsQueryHandler implements GetSentInvitationsQuery {
    private final IWorkspaceInvitationRepository invitationRepository;

    @Override
    public OutputPort<QueryResult<List<GetSentInvitationsResponse>>> execute(UUID userId, Pageable pageable, WorkspaceInvitationStatus status) {
        var result = invitationRepository.GetSentInvitations(userId, pageable, status);

        if (result.getResults().isEmpty()) return OutputPort.failure(HttpStatus.NO_CONTENT, null);

        return OutputPort.success(result, HttpStatus.OK, "Your invitations are ready");
    }
}
