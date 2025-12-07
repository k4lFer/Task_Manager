package com.pck4x.task_manager.modules.auth.infrastructure.services;

import com.pck4x.task_manager.modules.auth.interfaces.services.IJwtService;
import com.pck4x.task_manager.shared.security.ITokenProvider;
import com.pck4x.task_manager.shared.security.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtAuthService implements IJwtService {

    private final ITokenProvider tokenProvider;

    @Override
    public String generateAccessToken(Object userId) {
        return tokenProvider.generateToken(
                userId.toString(),
                new HashMap<>(),
                TokenType.ACCESS
        );
    }

    @Override
    public String generateRefreshToken(Object userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("token_type", "REFRESH_TOKEN");

        return tokenProvider.generateToken(
                userId.toString(),
                claims,
                TokenType.REFRESH
        );
    }

    @Override
    public String generateAccessTokenByRefreshToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken, TokenType.REFRESH)) {
            throw new RuntimeException("Invalid Refresh Token");
        }

        Object typeClaim = tokenProvider.extractClaim(refreshToken, "token_type", TokenType.REFRESH);

        if (typeClaim == null || !"REFRESH_TOKEN".equals(typeClaim.toString())) {
            throw new RuntimeException("Invalid Token Type: not a refresh token");
        }

        String userId = tokenProvider.extractSubject(refreshToken, TokenType.REFRESH);
        return generateAccessToken(userId);
    }
}