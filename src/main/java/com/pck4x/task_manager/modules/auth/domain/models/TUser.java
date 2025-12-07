package com.pck4x.task_manager.modules.auth.domain.models;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class TUser {

    private final UUID id;
    private final UUID personId;
    private final String username;
    private final String email;
    private final String password;
    private final Instant createdAt;
    private final Instant updatedAt;

    public static TUser create(UUID personId, String username, String email, String encodedPassword) {
        return TUser.builder()
                .id(UUID.randomUUID())
                .personId(personId)
                .username(username)
                .email(email)
                .password(encodedPassword)
                .createdAt(Instant.now())
                .updatedAt(null)
                .build();
    }

}