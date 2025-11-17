package com.pck4x.task_manager.modules.user.application.use_cases.output_port.command;

import com.pck4x.task_manager.shared.application.output_port.IHandleFailure;
import com.pck4x.task_manager.shared.application.output_port.IHandleSuccess;
import com.pck4x.task_manager.shared.application.output_port.IOutputPort;

import java.util.UUID;

public interface IRegisterAdminUserOutputPort extends IOutputPort<UUID> {
    void HandleSuccess(IHandleSuccess<UUID> success);
    void HandleFailure(IHandleFailure failure);
}
