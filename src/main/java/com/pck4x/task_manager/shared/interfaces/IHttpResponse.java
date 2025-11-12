package com.pck4x.task_manager.shared.interfaces;

import org.springframework.http.HttpStatusCode;

public interface IHttpResponse {
    HttpStatusCode getHttpStatusCode();
}
