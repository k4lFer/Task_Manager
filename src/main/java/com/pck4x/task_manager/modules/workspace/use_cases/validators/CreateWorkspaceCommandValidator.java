package com.pck4x.task_manager.modules.workspace.use_cases.validators;

import com.pck4x.task_manager.modules.workspace.objects.dtos.command.CreateWorkspaceDto;
import com.pck4x.task_manager.shared.interfaces.IInputPortValidator;
import com.pck4x.task_manager.shared.objects.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateWorkspaceCommandValidator implements IInputPortValidator<CreateWorkspaceDto> {

    private HttpStatusCode status;
    private List<MessageDto> message;

    @Override
    public boolean Validate(CreateWorkspaceDto input) {
        message.clear();

        if (input == null) {
            message.add(new MessageDto("NULL_INPUT", "Input data is required."));
            this.status = HttpStatus.BAD_REQUEST;
            return false;
        }

        if (input.getName() == null || input.getName().isBlank()) {
            message.add(new MessageDto("NAME_REQUIRED", "Workspace name is required."));
        }

        if (message.isEmpty()) {
            this.status = HttpStatus.UNPROCESSABLE_ENTITY;
            return false;
        }

        return true;
    }

    @Override
    public HttpStatusCode getHttpStatusCode() {
        return status;
    }

    @Override
    public List<MessageDto> getMessage() {
        return message;
    }
}
