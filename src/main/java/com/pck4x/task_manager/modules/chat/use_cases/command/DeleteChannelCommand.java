package com.pck4x.task_manager.modules.chat.use_cases.command;

import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface DeleteChannelCommand {
    OutputPort<Void> execute(UUID userId, UUID channelId);
}
