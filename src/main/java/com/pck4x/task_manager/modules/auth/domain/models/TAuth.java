package com.pck4x.task_manager.modules.auth.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TAuth {
    private UUID id;
    private String userName;
    private String password;

    private UUID userId;

    // Static method, create
    public static TAuth Create(String userName, String password, UUID userId) {
        return new TAuth(UUID.randomUUID(), userName, password, userId);
    }
}
