package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.use_cases.command.DeleteWorkspaceCommand;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class DeleteWorkspaceCommandHandler implements DeleteWorkspaceCommand {
    private final IWorkspaceRepository workspaceRepository;

    @Override
    public OutputPort<Void> execute(UUID userId, UUID workspaceId) {
        var workspace = workspaceRepository.getWorkspace(workspaceId);
        if (workspace.isEmpty()) return OutputPort.failure(HttpStatus.NOT_FOUND, "Workspace not found");

        if (workspace.get().getOwnerId().equals(userId)) {
            workspaceRepository.delete(workspace.get());
            return OutputPort.success(null, HttpStatus.OK, "Workspace deleted successfully");
        }

        return OutputPort.failure(HttpStatus.FORBIDDEN, "You are not the owner of this workspace");
    }
}
