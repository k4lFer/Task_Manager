package com.pck4x.task_manager.modules.board.use_cases.command;

import com.pck4x.task_manager.modules.board.objects.dtos.command.CreateLabelDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface CreateLabelCommand {
    OutputPort<UUID> execute(UUID userId, UUID boardId, CreateLabelDto input);
}
