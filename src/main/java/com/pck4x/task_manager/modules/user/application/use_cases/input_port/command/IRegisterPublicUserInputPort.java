package com.pck4x.task_manager.modules.user.application.use_cases.input_port.command;

import com.pck4x.task_manager.modules.user.application.dtos.input.RegisterPublicUserDto;

public interface IRegisterPublicUserInputPort {
    void Handle(RegisterPublicUserDto input);
}
