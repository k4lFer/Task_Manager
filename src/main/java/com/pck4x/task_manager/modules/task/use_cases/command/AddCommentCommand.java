package com.pck4x.task_manager.modules.task.use_cases.command;

import com.pck4x.task_manager.modules.task.objects.dtos.commands.AddCommentDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface AddCommentCommand {
    OutputPort<UUID> execute(UUID userId, UUID cardId, AddCommentDto input);
}
