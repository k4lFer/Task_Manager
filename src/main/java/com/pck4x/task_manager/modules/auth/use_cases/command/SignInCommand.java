package com.pck4x.task_manager.modules.auth.use_cases.command;

import com.pck4x.task_manager.modules.auth.objects.dtos.command.SignInDto;
import com.pck4x.task_manager.modules.auth.objects.dtos.output.SignInOutDto;
import com.pck4x.task_manager.shared.result.Result;

public interface SignInCommand {
    Result<SignInOutDto> execute(SignInDto input);
}
