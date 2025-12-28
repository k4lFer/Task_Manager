package com.pck4x.task_manager.modules.workspace.use_cases.validators;

import com.pck4x.task_manager.modules.workspace.objects.dtos.command.CreateWorkspaceDto;
import com.pck4x.task_manager.shared.interfaces.IInputPortValidator;
import com.pck4x.task_manager.shared.objects.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateWorkspaceCommandValidator implements IInputPortValidator<CreateWorkspaceDto> {

    private HttpStatusCode status;
    private List<MessageDto> message;

    @Override
    public boolean Validate(CreateWorkspaceDto input) {

        List<String> errors = new ArrayList<>();

        if (input == null) {
            errors.add("Input cannot be null");
            this.status = HttpStatusCode.valueOf(400);

        } else {
            if (input.getName() == null || input.getName().isBlank()) {
                errors.add("Name is required");
            }
        }

        if (!errors.isEmpty()) {
            this.status = HttpStatusCode.valueOf(400);
            this.message = List.of(
                    new MessageDto("VALIDATION_ERROR", errors)
            );
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
