package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import com.pck4x.task_manager.modules.workspace.use_cases.command.CancelWorkspaceInvitationCommand;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CancelWorkspaceInvitationCommandHandler implements CancelWorkspaceInvitationCommand {
    private final IWorkspaceInvitationRepository invitationRepository;

    @Override
    @Transactional
    public OutputPort<?> execute(UUID id, UUID invitationId) {
        var result = invitationRepository.findById(invitationId);

        if (result.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Invitation not found");
        }

        var invitation = result.get();

        if (!invitation.getInvitedBy().equals(id)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "You are not authorized to cancel this invitation");
        }

        if (invitation.getStatus() != WorkspaceInvitationStatus.PENDING) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "Invitation already processed");
        }

        invitation.cancel();
        invitationRepository.updateStatus(invitation);

        return OutputPort.success(null, HttpStatus.OK, "Invitation canceled");
    }
}
