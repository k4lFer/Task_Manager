package com.pck4x.task_manager.shared.interfaces;

public interface IInputPortValidator<T> extends IHttpResponse, IMessageDto {
    boolean Validate(T input);
}
