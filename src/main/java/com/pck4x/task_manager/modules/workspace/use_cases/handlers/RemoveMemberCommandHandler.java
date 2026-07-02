package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceMemberRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.services.IWorkspaceAccessService;
import com.pck4x.task_manager.modules.workspace.use_cases.command.RemoveMemberCommand;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RemoveMemberCommandHandler implements RemoveMemberCommand {

    private final IWorkspaceRepository workspaceRepository;
    private final IWorkspaceMemberRepository workspaceMemberRepository;
    private final IWorkspaceAccessService workspaceAccessService;

    @Override
    public OutputPort<Void> execute(UUID userId, UUID workspaceId, UUID memberId) {
        var workspaceOpt = workspaceRepository.getWorkspace(workspaceId);
        if (workspaceOpt.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Workspace not found");
        }

        var workspace = workspaceOpt.get();

        if (!workspaceAccessService.isAdminOrOwner(workspaceId, userId)) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "Only workspace owner or admins can remove members");
        }

        if (workspace.getOwnerId().equals(memberId)) {
            return OutputPort.failure(HttpStatus.BAD_REQUEST, "Cannot remove the workspace owner");
        }

        var memberOpt = workspaceMemberRepository.findByWorkspaceIdAndMemberId(workspaceId, memberId);
        if (memberOpt.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Member not found in workspace");
        }

        workspaceMemberRepository.deleteByWorkspaceIdAndMemberId(workspaceId, memberId);
        workspace.removeMember(memberId);

        return OutputPort.success(null, HttpStatus.OK, "Member removed successfully");
    }
}
