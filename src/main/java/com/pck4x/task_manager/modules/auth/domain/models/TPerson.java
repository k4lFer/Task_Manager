package com.pck4x.task_manager.modules.auth.domain.models;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
public class TPerson {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final Date birthDate;
    private final Date createdAt;
    private Date updatedAt;

    private TUser user;

    public static TPerson create(String firstName, String lastName, Date birthDate) {
        return TPerson.builder()
                .id(UUID.randomUUID())
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    public void attachUser(TUser user) {
        this.user = user;
        this.updatedAt = new Date();
    }
}