package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.use_cases.command.DeleteWorkspaceCommand;
import com.pck4x.task_manager.shared.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class DeleteWorkspaceCommandHandler implements DeleteWorkspaceCommand {
    private final IWorkspaceRepository workspaceRepository;

    @Override
    public Result<Void> execute(UUID userId, UUID workspaceId) {
        var workspace = workspaceRepository.getWorkspace(workspaceId);
        if (workspace.isEmpty()) return Result.notFound("Workspace not found");

        if (workspace.get().getOwnerId().equals(userId)) {
            workspaceRepository.delete(workspace.get());
            return Result.success(null, "Workspace deleted successfully");
        }

        return Result.error("You are not the owner of this workspace");
    }
}
