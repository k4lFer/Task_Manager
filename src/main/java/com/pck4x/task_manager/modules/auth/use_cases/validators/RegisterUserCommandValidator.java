package com.pck4x.task_manager.modules.auth.use_cases.validators;

import com.pck4x.task_manager.modules.auth.objects.dtos.command.RegisterUserDto;
import com.pck4x.task_manager.shared.interfaces.IInputPortValidator;
import com.pck4x.task_manager.shared.objects.MessageDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterUserCommandValidator implements IInputPortValidator<RegisterUserDto> {
    private HttpStatusCode httpStatusCode;
    private List<MessageDto> message;

    @Override
    public boolean Validate(RegisterUserDto input) {
        message.clear();

        if (input == null) {
            message.add(new MessageDto("NULL_INPUT", "Input data is required."));
            httpStatusCode = HttpStatusCode.valueOf(400);
            return false;
        }

        return true;

    }

    @Override
    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    @Override
    public List<MessageDto> getMessage() {
        return message;
    }
}
