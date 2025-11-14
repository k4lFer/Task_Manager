package com.pck4x.task_manager.shared.result;

import com.pck4x.task_manager.shared.interfaces.IOutput;
import com.pck4x.task_manager.shared.objects.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Collections;
import java.util.List;

public record Result(
        Object data,
        boolean isSuccess,
        HttpStatusCode status,
        List<MessageDto> messages
    ) implements IOutput {

    private Result(Object data, boolean isSuccess, HttpStatusCode status, MessageDto message) {
        this(data, isSuccess, status, message != null ? List.of(message) : Collections.emptyList());
    }

    @Override
    public HttpStatusCode getHttpStatusCode() {
        return status;
    }

    @Override
    public List<MessageDto> getMessage() {
        return messages;
    }

    public static Result success(Object data, String msg) {
        return new Result(
                data,
                true,
                HttpStatus.OK,
                new MessageDto("success", msg == null ? "Resource retrieved successfully" : msg)
        );
    }

    public static Result create(Object data, String msg) {
        return new Result(
                data,
                true,
                HttpStatus.CREATED,
                new MessageDto("success", msg == null ? "Resource created successfully" : msg)
        );
    }

    public static Result noContent() {
        return new Result(
                null,
                true,
                HttpStatus.NO_CONTENT,
                (List<MessageDto>) null
        );
    }

    public static Result error(String msg) {
        return new Result(
                null,
                false,
                HttpStatus.BAD_REQUEST,
                new MessageDto("error", msg == null ? "" : msg)
        );
    }

    public static Result notFound(String msg) {
        return new Result(
                null,
                false,
                HttpStatus.NOT_FOUND,
                new MessageDto("NotFound", msg == null ? "Resource not found" : msg)
        );
    }

    public static <T> Result exception(String msg) {
        return new Result(
                null,
                false,
                HttpStatus.INTERNAL_SERVER_ERROR,
                new MessageDto("Exception", msg == null ? "An internal server error occurred." : msg)
        );
    }
}
