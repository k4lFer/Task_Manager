package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.auth.interfaces.services.IUserService;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceInvitation;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceInvitationRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceMemberRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import com.pck4x.task_manager.modules.workspace.objects.dtos.command.SendWorkspaceInvitationDto;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationRole;
import com.pck4x.task_manager.modules.workspace.use_cases.command.SendWorkspaceInvitationCommand;
import com.pck4x.task_manager.shared.result.Result;
import lombok.RequiredArgsConstructor;
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
    public Result<UUID> execute(UUID invitedBy, SendWorkspaceInvitationDto input) {
        var user = userService.getUserByExactEmail(input.getEmail());

        if(user.isEmpty()) return Result.notFound("User not found");

        if (!workspaceAccessService.isAdminOrOwner(input.getWorkspaceId(), invitedBy)) {
            return Result.forbidden("Only Workspace Owner or Admins can add members");
        }

        boolean isInviterOwner = workspaceAccessService.isOwner(input.getWorkspaceId(), invitedBy);

        if (input.getRole() == WorkspaceInvitationRole.ADMIN && !isInviterOwner) {
            return Result.forbidden(
                    "Only Workspace Owner can invite an ADMIN"
            );
        }

        if (workspaceInvitationRepository.existsPending(
                input.getWorkspaceId(),
                user.get().id(),
                user.get().email()
        )) {
            return Result.forbidden("There is already a pending invitation for this user");
        }

        var member = workspaceMemberRepository.findByWorkspaceIdAndMemberId(input.getWorkspaceId(), user.get().id());

        if(member.isPresent()){
            return Result.forbidden("User is already a member of this workspace");
        }

        var invitation = TWorkspaceInvitation.create(
                input.getWorkspaceId(),
                invitedBy,
                user.get().id(),
                user.get().email(),
                input.getRole()
        );

        var saved = workspaceInvitationRepository.save(invitation);

        if (saved != null) return Result.success(saved.getId(), "Invitation sent successfully");

        return Result.error("Something went wrong");
    }
}
