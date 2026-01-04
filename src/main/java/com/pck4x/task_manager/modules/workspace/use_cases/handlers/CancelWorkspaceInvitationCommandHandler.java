package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import com.pck4x.task_manager.modules.workspace.use_cases.command.CancelWorkspaceInvitationCommand;
import com.pck4x.task_manager.shared.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CancelWorkspaceInvitationCommandHandler implements CancelWorkspaceInvitationCommand {
    private final IWorkspaceInvitationRepository invitationRepository;

    @Override
    @Transactional
    public Result<?> execute(UUID id, UUID invitationId) {
        var result = invitationRepository.findById(invitationId);

        if (result.isEmpty()) {
            return Result.notFound("Invitation not found");
        }

        var invitation = result.get();

        if (!invitation.getInvitedBy().equals(id)) {
            return Result.forbidden("You are not authorized to cancel this invitation");
        }

        if (invitation.getStatus() != WorkspaceInvitationStatus.PENDING) {
            return Result.forbidden("Invitation already processed");
        }

        invitation.cancel();
        invitationRepository.updateStatus(invitation);

        return Result.success(null, "Invitation canceled");
    }
}
