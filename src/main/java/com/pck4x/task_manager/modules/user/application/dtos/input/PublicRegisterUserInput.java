package com.pck4x.task_manager.modules.user.application.dtos.input;

import lombok.Getter;

import java.util.Date;

@Getter
public class PublicRegisterUserInput {
    public String firstName;
    public String lastName;
    public Date birthDate;
    public String userName;
    public String password;
}
