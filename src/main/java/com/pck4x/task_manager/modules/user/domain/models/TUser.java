package com.pck4x.task_manager.modules.user.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TUser {
    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public static TUser Create(String firstName, String lastName, Date birthDate){
        return new TUser(UUID.randomUUID(), firstName, lastName, birthDate);
    }
}
