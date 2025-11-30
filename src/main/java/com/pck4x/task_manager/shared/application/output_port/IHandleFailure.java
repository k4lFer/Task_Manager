package com.pck4x.task_manager.shared.application.output_port;

import com.pck4x.task_manager.shared.objects.MessageDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IHandleFailure {
    List<MessageDto> messages();
    HttpStatus status();

    /*static IHandleFailure Fail(List<MessageDto> messages, HttpStatus status) {
        return new IHandleFailure() {
            @Override public List<MessageDto> messages() { return messages; }
            @Override public HttpStatus status() { return status; }
        };
    }

    static IHandleFailure Fail(String message, HttpStatus status) {
        return Fail(List.of(new MessageDto(message)), status);
    }*/
}
