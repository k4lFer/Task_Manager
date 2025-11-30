package com.pck4x.task_manager.shared.interfaces;

public interface IOutput<T> extends IHttpResponse, IMessageDto{
    T data();
    boolean isSuccess();
}
