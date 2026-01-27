package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.auth.interfaces.services.IUserService;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceMemberRepository;
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
    private final IUserService userService;
    private final IWorkspaceMemberRepository memberRepository;
    private final IWorkspaceInvitationRepository invitationRepository;

    @Override
    public OutputPort<QueryResult<List<CheckWorkspaceInvitationResponse>>> execute(UUID workspaceId, String query, Pageable pageable) {
        var users = userService.searchByEmailPrefix(query, pageable);

        var mapped = users.getResults().stream().map(user -> {

                    boolean alreadyMember =
                            memberRepository
                                    .findByWorkspaceIdAndMemberId(
                                            workspaceId, user.id())
                                    .isPresent();

                    boolean pendingInvitation =
                            invitationRepository.existsPending(
                                    workspaceId,
                                    user.id(),
                                    user.email()
                            );

                    boolean canInvite =
                            !alreadyMember && !pendingInvitation;

                    String reason =
                            alreadyMember
                                    ? "Already a workspace member"
                                    : pendingInvitation
                                    ? "Pending invitation"
                                    : "User can be invited";

                    String fullName = String.join(" ",
                            user.firstName(),
                            user.lastName()
                    ).trim();

                    return new CheckWorkspaceInvitationResponse(
                            canInvite,
                            alreadyMember,
                            pendingInvitation,
                            user.id(),
                            user.email(),
                            fullName,
                            reason
                    );
                }).toList();

        if (mapped.isEmpty()) return OutputPort.failure(HttpStatus.NOT_FOUND, "Users not found");

        return OutputPort.success(QueryResult.success(
                mapped,
                users.getTotalCounts(),
                users.getTotalPages(),
                users.getPageNumber(),
                users.getPageSize()
        ), HttpStatus.OK, "Users are ready");
    }
}
