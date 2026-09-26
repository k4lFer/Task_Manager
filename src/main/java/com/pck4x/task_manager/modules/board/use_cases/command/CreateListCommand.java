package com.pck4x.task_manager.modules.board.use_cases.command;

import com.pck4x.task_manager.modules.board.objects.dtos.command.CreateListDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface CreateListCommand {
    OutputPort<UUID> execute(UUID userId, UUID boardId, CreateListDto input);
}
