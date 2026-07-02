package com.pck4x.task_manager.modules.task.use_cases.command;

import com.pck4x.task_manager.modules.task.objects.dtos.commands.UpdateCardDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface UpdateCardCommand {
    OutputPort<Void> execute(UUID userId, UUID cardId, UpdateCardDto input);
}
