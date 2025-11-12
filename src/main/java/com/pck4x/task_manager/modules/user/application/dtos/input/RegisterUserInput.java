package com.pck4x.task_manager.modules.user.application.dtos.input;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegisterUserInput {
    public String firstName;
    public String lastName;
    public Date birthDate;
    public String userName;
    public String password;
}
