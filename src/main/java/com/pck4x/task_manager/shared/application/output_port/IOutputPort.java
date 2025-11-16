package com.pck4x.task_manager.shared.application.output_port;

import com.pck4x.task_manager.shared.interfaces.IHttpResponse;
import com.pck4x.task_manager.shared.interfaces.IMessageDto;

public interface IOutputPort<T> extends IHttpResponse, IMessageDto {
    boolean isSuccess();
    T getData();
}
