package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.use_cases.command.AcceptWorkspaceInvitationCommand;
import com.pck4x.task_manager.shared.application.adapter.DomainEventPublisher;
import com.pck4x.task_manager.shared.result.Result;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AcceptWorkspaceInvitationCommandHandler implements AcceptWorkspaceInvitationCommand {
    private final IWorkspaceInvitationRepository invitationRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    @Transactional
    public Result execute(UUID id) {
        var invitation = invitationRepository.findById(id);

        invitation.accept();

        invitationRepository.save(invitation);

        domainEventPublisher.publishFrom(invitation);

        return Result.success(invitation.getId(), "Invitation accepted");
    }
}
