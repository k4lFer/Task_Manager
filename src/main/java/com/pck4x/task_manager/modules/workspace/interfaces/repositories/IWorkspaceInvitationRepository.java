package com.pck4x.task_manager.modules.workspace.interfaces.repositories;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceInvitation;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.GetReceivedInvitationsResponse;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.Response.GetSentInvitationsResponse;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWorkspaceInvitationRepository {
    TWorkspaceInvitation save(TWorkspaceInvitation tWorkspaceInvitation);
    TWorkspaceInvitation updateStatus(TWorkspaceInvitation tWorkspaceInvitation);
    Boolean existsPending(UUID workspaceId, UUID memberId, String email);
    Boolean existsPendingByEmail(UUID workspaceId, String email);
    Optional<TWorkspaceInvitation> findById(UUID id);
    QueryResult<List<GetReceivedInvitationsResponse>> GetReceivedInvitations(UUID userId, Pageable pageable, WorkspaceInvitationStatus status);
    QueryResult<List<GetSentInvitationsResponse>> GetSentInvitations(UUID userId, Pageable pageable, WorkspaceInvitationStatus status);
}
