package com.pck4x.task_manager.modules.task.use_cases.command;

import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface RemoveLabelCommand {
    OutputPort<Void> execute(UUID userId, UUID cardId, UUID labelId);
}
