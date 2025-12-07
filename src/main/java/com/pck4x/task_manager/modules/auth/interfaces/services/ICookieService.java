package com.pck4x.task_manager.modules.auth.interfaces.services;

import jakarta.servlet.http.HttpServletResponse;

public interface ICookieService {
    void setRefreshTokenCookie(HttpServletResponse response, String token);
    void removeRefreshTokenCookie(HttpServletResponse response);
}
