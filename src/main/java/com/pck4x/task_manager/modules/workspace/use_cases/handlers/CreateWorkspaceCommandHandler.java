package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.objects.dtos.command.CreateWorkspaceDto;
import com.pck4x.task_manager.modules.workspace.use_cases.command.CreateWorkspaceCommand;
import com.pck4x.task_manager.shared.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateWorkspaceCommandHandler implements CreateWorkspaceCommand {
    private final IWorkspaceRepository workspaceRepository;

    @Override
    public Result<UUID> execute(UUID userId, CreateWorkspaceDto input) {
        var workspace = TWorkspace.create(
                userId,
                input.getName(),
                input.getDescription(),
                input.getIsPrivate()
        );

        var workspaceMember = TWorkspaceMembers.create(
                workspace.getId(),
                userId
        );
        workspace.attachWorkspace(workspaceMember);

        var saved = workspaceRepository.create(workspace);
        if (saved != null) return Result.success(saved.getId(), "Workspace created successfully");

        return Result.error("Something went wrong");
    }
}
