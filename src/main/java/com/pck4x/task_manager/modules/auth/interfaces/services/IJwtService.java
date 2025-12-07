package com.pck4x.task_manager.modules.auth.interfaces.services;

public interface IJwtService {
    String generateAccessToken(Object data);
    String generateRefreshToken(Object data);
    String generateAccessTokenByRefreshToken(String refreshToken);
}
