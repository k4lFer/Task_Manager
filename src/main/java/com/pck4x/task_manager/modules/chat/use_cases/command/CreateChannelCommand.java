package com.pck4x.task_manager.modules.chat.use_cases.command;

import com.pck4x.task_manager.modules.chat.objects.dtos.command.CreateChatChannelDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface CreateChannelCommand {
    OutputPort<UUID> execute(UUID ownerId, CreateChatChannelDto input);
}
