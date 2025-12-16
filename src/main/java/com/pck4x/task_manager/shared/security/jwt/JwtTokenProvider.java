package com.pck4x.task_manager.shared.security.jwt;

import com.pck4x.task_manager.shared.security.ITokenProvider;
import com.pck4x.task_manager.shared.security.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtTokenProvider implements ITokenProvider {

    // --- Inyección de Propiedades ---
    @Value("${security.jwt.auth.access.secret}")
    private String accessSecret;
    @Value("${security.jwt.auth.access.expiration}")
    private long accessExpiration;

    @Value("${security.jwt.auth.refresh.secret}")
    private String refreshSecret;
    @Value("${security.jwt.auth.refresh.expiration}")
    private long refreshExpiration;

    @Value("${security.jwt.workspace.invite.secret}")
    private String inviteSecret;
    @Value("${security.jwt.workspace.invite.expiration}")
    private long inviteExpiration;

    @Override
    public String generateToken(String subject, Map<String, Object> claims, TokenType type) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + getExpiration(type)))
                .signWith(getKey(type), SignatureAlgorithm.HS256) // Firma con la llave correcta
                .compact();
    }

    @Override
    public boolean validateToken(String token, TokenType type) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey(type))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String extractSubject(String token, TokenType type) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey(type))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    @Override
    public Object extractClaim(String token, String claimKey, TokenType type) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey(type))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get(claimKey);
    }


    private Key getKey(TokenType type) {
        String secret = switch (type) {
            case ACCESS -> accessSecret;
            case REFRESH -> refreshSecret;
            case INVITATION -> inviteSecret;
        };
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private long getExpiration(TokenType type) {
        return switch (type) {
            case ACCESS -> accessExpiration;
            case REFRESH -> refreshExpiration;
            case INVITATION -> inviteExpiration;
        };
    }
}