package com.pck4x.task_manager.modules.task.use_cases.command;

import com.pck4x.task_manager.modules.task.objects.dtos.commands.AddAssignmentDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface AddAssignmentCommand {
    OutputPort<UUID> execute(UUID userId, UUID cardId, AddAssignmentDto input);
}
