package com.pck4x.task_manager.modules.auth.domain.models;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class TPerson {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final Instant createdAt;
    private Instant updatedAt;

    private TUser user;

    public static TPerson create(String firstName, String lastName, LocalDate birthDate) {
        return TPerson.builder()
                .id(UUID.randomUUID())
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .createdAt(Instant.now())
                .updatedAt(null)
                .build();
    }

    public void attachUser(TUser user) {
        this.user = user;
        this.updatedAt = Instant.now();
    }
}