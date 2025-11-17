package com.pck4x.task_manager.modules.user.application.presenter.command;

import com.pck4x.task_manager.modules.user.application.use_cases.output_port.command.IRegisterAdminUserOutputPort;
import com.pck4x.task_manager.shared.application.output_port.IHandleFailure;
import com.pck4x.task_manager.shared.application.output_port.IHandleSuccess;
import com.pck4x.task_manager.shared.objects.MessageDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RegisterAdminUserPresenter implements IRegisterAdminUserOutputPort {
    private boolean isSuccess;
    private UUID data;
    private List<MessageDto> messages;
    private HttpStatusCode status;

    @Override
    public void HandleSuccess(IHandleSuccess<UUID> success) {
        this.data = success.data();
        this.messages = success.messages();
        this.status = success.status();
        this.isSuccess = true;
    }

    @Override
    public void HandleFailure(IHandleFailure failure) {
        this.messages = failure.messages();
        this.status = failure.status();
        this.isSuccess = false;
    }

    @Override
    public boolean isSuccess() {
        return this.isSuccess;
    }

    @Override
    public UUID getData() {
        return this.data;
    }

    @Override
    public HttpStatusCode getHttpStatusCode() {
        return this.status;
    }

    @Override
    public List<MessageDto> getMessage() {
        return this.messages;
    }
}
