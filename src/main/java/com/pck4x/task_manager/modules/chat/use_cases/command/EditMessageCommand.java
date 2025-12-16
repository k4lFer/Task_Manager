package com.pck4x.task_manager.modules.chat.use_cases.command;

import com.pck4x.task_manager.modules.chat.objects.dtos.command.EditMessageDto;
import com.pck4x.task_manager.shared.result.Result;

import java.util.UUID;

public interface EditMessageCommand {
    Result<?> execute(UUID editorId, EditMessageDto input);
}
