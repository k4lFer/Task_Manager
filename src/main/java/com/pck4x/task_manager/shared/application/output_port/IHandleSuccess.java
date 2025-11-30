package com.pck4x.task_manager.shared.application.output_port;

import com.pck4x.task_manager.shared.objects.MessageDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IHandleSuccess<T> {
    T data();
    List<MessageDto> messages();
    HttpStatus status();
/*
    static <T> IHandleSuccess<T> HandleSuccess(T data, HttpStatus status, String message) {
        return new IHandleSuccess<>() {
            @Override public T data() {
                return data;
            }
            @Override public List<MessageDto> messages() {
                return List.of(new MessageDto(message));
            }
            @Override public HttpStatus status() {
                return status;
            }
        };
    }
*/
}

