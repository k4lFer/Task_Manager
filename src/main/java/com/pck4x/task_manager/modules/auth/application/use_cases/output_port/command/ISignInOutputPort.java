package com.pck4x.task_manager.modules.auth.application.use_cases.output_port.command;

import com.pck4x.task_manager.modules.auth.application.dtos.output.SignInOutputDto;
import com.pck4x.task_manager.shared.application.output_port.IHandleFailure;
import com.pck4x.task_manager.shared.application.output_port.IHandleSuccess;
import com.pck4x.task_manager.shared.application.output_port.IOutputPort;

public interface ISignInOutputPort extends IOutputPort<SignInOutputDto> {
    void HandleSuccess(IHandleSuccess<SignInOutputDto> success);
    void HandleFailure(IHandleFailure failure);
}
