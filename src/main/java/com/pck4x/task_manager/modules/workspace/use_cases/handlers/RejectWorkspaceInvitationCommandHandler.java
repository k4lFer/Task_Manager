package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import com.pck4x.task_manager.modules.workspace.use_cases.command.RejectWorkspaceInvitationCommand;
import com.pck4x.task_manager.shared.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RejectWorkspaceInvitationCommandHandler implements RejectWorkspaceInvitationCommand {
    private final IWorkspaceInvitationRepository invitationRepository;

    @Override
    @Transactional
    public Result<?> execute(UUID id, UUID invitationId) {
        var result = invitationRepository.findById(invitationId);

        if (result.isEmpty())
            return Result.notFound("Invitation not found");

        var invitation = result.get();

        if (!id.equals(invitation.getInvitedUserId()))
            return Result.forbidden("You are not authorized to reject this invitation");

        if (invitation.getStatus() != WorkspaceInvitationStatus.PENDING)
            return Result.forbidden("Invitation already processed");

        if (invitation.getExpiresAt() != null &&
                invitation.getExpiresAt().isBefore(Instant.now())) {

            invitation.expired();
            invitationRepository.updateStatus(invitation);

            return Result.forbidden("Invitation expired");
        }

        invitation.rejected();
        invitationRepository.updateStatus(invitation);

        return Result.success(null, "Invitation rejected");
    }
}
