package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.auth.interfaces.services.IUserService;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceInvitation;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceMemberRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import com.pck4x.task_manager.modules.workspace.objects.dtos.command.SendWorkspaceInvitationDto;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationRole;
import com.pck4x.task_manager.modules.workspace.use_cases.command.SendWorkspaceInvitationCommand;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SendWorkspaceInvitationCommandHandler implements SendWorkspaceInvitationCommand {
    private final IWorkspaceAccessService workspaceAccessService;
    private final IWorkspaceMemberRepository workspaceMemberRepository;
    private final IWorkspaceInvitationRepository workspaceInvitationRepository;
    private final IUserService userService;

    @Override
    public OutputPort<UUID> execute(UUID invitedBy, SendWorkspaceInvitationDto input) {
        var user = userService.getUserByExactEmail(input.getEmail());

        if(user.isEmpty()) return OutputPort.failure(HttpStatus.NOT_FOUND, "User not found");

        if (!workspaceAccessService.isAdminOrOwner(input.getWorkspaceId(), invitedBy)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "Only Workspace Owner or Admins can add members");
        }

        boolean isInviterOwner = workspaceAccessService.isOwner(input.getWorkspaceId(), invitedBy);

        if (input.getRole() == WorkspaceInvitationRole.ADMIN && !isInviterOwner) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "Only Workspace Owner can invite an ADMIN");
        }

        if (workspaceInvitationRepository.existsPending(
                input.getWorkspaceId(),
                user.get().id(),
                user.get().email()
        )) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "There is already a pending invitation for this user");
        }

        var member = workspaceMemberRepository.findByWorkspaceIdAndMemberId(input.getWorkspaceId(), user.get().id());

        if(member.isPresent()){
            return OutputPort.failure(HttpStatus.FORBIDDEN, "User is already a member of this workspace");
        }

        var invitation = TWorkspaceInvitation.create(
                input.getWorkspaceId(),
                invitedBy,
                user.get().id(),
                user.get().email(),
                input.getRole()
        );

        var saved = workspaceInvitationRepository.save(invitation);

        if (saved != null) return OutputPort.success(saved.getId(), HttpStatus.CREATED, "Invitation sent successfully");

        return OutputPort.failure(HttpStatus.BAD_REQUEST, "Something went wrong");
    }
}
