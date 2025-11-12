package com.pck4x.task_manager.shared.interfaces;

public interface IOutput extends IHttpResponse, IMessageDto{
    Object data();
    boolean isSuccess();
}
