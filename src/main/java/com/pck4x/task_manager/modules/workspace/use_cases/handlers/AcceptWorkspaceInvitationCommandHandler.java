package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationStatus;
import com.pck4x.task_manager.modules.workspace.use_cases.command.AcceptWorkspaceInvitationCommand;
import com.pck4x.task_manager.shared.application.adapter.DomainEventPublisher;
import com.pck4x.task_manager.shared.result.Result;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AcceptWorkspaceInvitationCommandHandler implements AcceptWorkspaceInvitationCommand {
    private final IWorkspaceInvitationRepository invitationRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    @Transactional
    public Result execute(UUID id, UUID invitationId) {
        var result = invitationRepository.findById(invitationId);

        if (result.isEmpty()) return Result.notFound("Invitation not found");

        var invitation = result.get();

        if (invitation.getExpiresAt().isBefore(Instant.now())) return Result.forbidden("Invitation expired");

        if (invitation.getStatus() != WorkspaceInvitationStatus.PENDING) return Result.forbidden("Invitation already processed");

        if (!invitation.getInvitedUserId().equals(id)) return Result.error("You are not authorized to accept this invitation");

        invitation.accept();

        invitationRepository.save(invitation);

        domainEventPublisher.publishFrom(invitation);

        return Result.success(invitation.getId(), "Invitation accepted");
    }
}
