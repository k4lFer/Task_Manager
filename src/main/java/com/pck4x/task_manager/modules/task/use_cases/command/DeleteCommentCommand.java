package com.pck4x.task_manager.modules.task.use_cases.command;

import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface DeleteCommentCommand {
    OutputPort<Void> execute(UUID userId, UUID commentId);
}
