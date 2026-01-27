package com.pck4x.task_manager.modules.chat.use_cases.command;

import com.pck4x.task_manager.modules.chat.objects.dtos.command.SendMessageDto;
import com.pck4x.task_manager.shared.result.OutputPort;

import java.util.UUID;

public interface SendMessageCommand {
    OutputPort execute(UUID sendId, SendMessageDto input);
}
