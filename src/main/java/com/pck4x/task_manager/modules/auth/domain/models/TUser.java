package com.pck4x.task_manager.modules.auth.domain.models;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class TUser {

    private final UUID id;
    private final TPerson person;
    private final String username;
    private final String email;
    private final String password;
    private final Instant createdAt;
    private final Instant updatedAt;

    private TUser(UUID id, TPerson person, String username, String email, String password, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.person = person;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static TUser create(String firstName, String lastName, LocalDate birthDate,
                               String username, String email, String password) {

        var person = TPerson.create(firstName, lastName, birthDate);
        var user = new TUser(
                UUID.randomUUID(),
                person,
                username,
                email,
                password,
                Instant.now(),
                null
        );

        person.attachUser(user);

        return user;
    }

}