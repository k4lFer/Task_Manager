package com.pck4x.task_manager.modules.task.use_cases.validators;

import com.pck4x.task_manager.modules.task.objects.dtos.commands.CreateCardDto;
import com.pck4x.task_manager.shared.interfaces.IInputPortValidator;
import com.pck4x.task_manager.shared.objects.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateCardCommandValidator implements IInputPortValidator<CreateCardDto> {
    private HttpStatusCode status;
    private final List<MessageDto> message = new ArrayList<>();

    @Override
    public boolean Validate(CreateCardDto input) {
        message.clear();

        if (input == null) {
            message.add(new MessageDto("NULL_INPUT", "Input data is required."));
            this.status = HttpStatus.BAD_REQUEST;
            return false;
        }

        if (input.getListId() == null) {
            message.add(new MessageDto("LIST_ID_REQUIRED", "List ID is required."));
        }

        if (input.getTitle() == null || input.getTitle().isBlank()) {
            message.add(new MessageDto("TITLE_REQUIRED", "Title is required."));
        }

        if (!message.isEmpty()) {
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
