package com.pck4x.task_manager.modules.workspace.use_cases.event_handler;

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

    @Override
    public Result<UUID> execute(UUID id, AddMemberDto input) {
        if(!workspaceAccessService.isAdminOrOwner(input.getWorkspaceId(), id)) {
            return Result.error("Only Workspace Owner or Admins can add members");
        }
        if(input.getRole().equals(WorkspaceMemberRole.OWNER)){
            
        }

        return null;
    }
}
