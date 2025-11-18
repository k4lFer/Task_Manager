package com.pck4x.task_manager.modules.auth.application.use_cases.input_port.command;

import com.pck4x.task_manager.modules.auth.application.dtos.input.SignInInputDto;

public interface ISignInInputPort {
    void Handle(SignInInputDto input);
}
