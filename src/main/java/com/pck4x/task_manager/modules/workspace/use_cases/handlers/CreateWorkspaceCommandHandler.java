package com.pck4x.task_manager.modules.workspace.use_cases.handlers;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.objects.dtos.command.CreateWorkspaceDto;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceMemberRole;
import com.pck4x.task_manager.modules.workspace.use_cases.command.CreateWorkspaceCommand;
import com.pck4x.task_manager.shared.interfaces.IInputPortValidator;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateWorkspaceCommandHandler implements CreateWorkspaceCommand {
    private final IWorkspaceRepository workspaceRepository;
    private final IInputPortValidator<CreateWorkspaceDto> validator;

    @Override
    public OutputPort<UUID> execute(UUID userId, CreateWorkspaceDto input) {
        if (!validator.Validate(input)) return OutputPort.failures(validator.getHttpStatusCode(), validator.getMessage());

       /* var workspace = TWorkspace.create(
                userId,
                input.getName(),
                input.getDescription(),
                input.getIsPrivate()
        );

        var workspaceMember = TWorkspaceMembers.create(
                workspace.getId(),
                userId,
                WorkspaceMemberRole.OWNER
        );
        workspace.attachWorkspace(workspaceMember);*/
        var workspace = TWorkspace.create(
                userId,
                input.getName(),
                input.getDescription(),
                input.getIsPrivate()
        );

        var saved = workspaceRepository.create(workspace);
        if (saved != null) return OutputPort.success(saved.getId(), HttpStatus.CREATED, "Workspace created successfully");

        return OutputPort.failure(HttpStatus.BAD_REQUEST, "Unable to create workspace");
    }
}
