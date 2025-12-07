package com.pck4x.task_manager.shared.security;

import java.util.Map;

public interface ITokenProvider {
    /**
     * Genera un token firmado.
     * @param subject El identificador principal (userId, email, invitationId, etc.)
     * @param claims Datos adicionales variables (roles, scope, expiry custom, etc.)
     * @return El String del token.
     */
    String generateToken(String subject, Map<String, Object> claims, TokenType type);

    /**
     * Valida si un token es correcto.
     */
    boolean validateToken(String token, TokenType type);

    /**
     * Extrae el subject (lo que pasaste como primer parámetro).
     */
    String extractSubject(String token, TokenType type);
    Object extractClaim(String token, String claimKey, TokenType type);
}
