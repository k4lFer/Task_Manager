package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceMemberRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import com.pck4x.task_manager.modules.workspace.objects.dtos.command.AddMemberDto;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceMemberRole;
import com.pck4x.task_manager.modules.workspace.use_cases.command.AddMemberCommand;
import com.pck4x.task_manager.shared.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddMemberCommandHandler implements AddMemberCommand {
    private final IWorkspaceAccessService workspaceAccessService;
    private final IWorkspaceMemberRepository workspaceMemberRepository;

    @Override
    public Result<UUID> execute(UUID id, AddMemberDto input) {
        if(!workspaceAccessService.isAdminOrOwner(input.getWorkspaceId(), id)) {
            return Result.forbidden("Only Workspace Owner or Admins can add members");
        }

        if(input.getRole().equals(WorkspaceMemberRole.OWNER)){
            return Result.forbidden("You cannot add an Owner to a workspace. Workspace can only have one OWNER.");
        }

        if(input.getRole().equals(WorkspaceMemberRole.ADMIN)){
            return Result.forbidden("You cannot add an Admin to a workspace. Workspace can only have one ADMIN.");
        }

        var member = workspaceMemberRepository.findByWorkspaceIdAndMemberId(input.getWorkspaceId(), input.getMemberId());
        
        if(member.isPresent()){
            return Result.forbidden("User is already a member of this workspace");
        }


        


        return null;
    }
}
