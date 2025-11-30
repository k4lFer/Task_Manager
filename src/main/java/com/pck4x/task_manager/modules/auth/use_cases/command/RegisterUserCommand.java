package com.pck4x.task_manager.modules.auth.use_cases.command;

import com.pck4x.task_manager.modules.auth.objects.dtos.command.RegisterUserDto;
import com.pck4x.task_manager.shared.result.Result;

import java.util.UUID;

public interface RegisterUserCommand {
    Result<UUID> execute(RegisterUserDto input);
}
