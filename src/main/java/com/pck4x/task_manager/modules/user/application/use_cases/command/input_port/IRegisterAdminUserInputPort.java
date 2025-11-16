package com.pck4x.task_manager.modules.user.application.use_cases.command.input_port;

import com.pck4x.task_manager.modules.user.application.dtos.input.RegisterUserInput;

public interface IRegisterAdminUserInputPort {
    void Handle(RegisterUserInput input);
}
