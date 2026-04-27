package com.pck4x.task_manager.modules.task.use_cases.command;

import com.pck4x.task_manager.modules.task.objects.dtos.commands.CreateCardDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface CreateCardCommand {
    OutputPort<UUID> execute(UUID userId, CreateCardDto input);
}
