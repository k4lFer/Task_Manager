package com.pck4x.task_manager.modules.task.use_cases.command;

import com.pck4x.task_manager.modules.task.objects.dtos.commands.AssignmentDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface AssignmentCommand {
    OutputPort<UUID> execute(UUID userId, UUID workspaceId, AssignmentDto input);
}
