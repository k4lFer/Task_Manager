package com.pck4x.task_manager.modules.auth.objects.dtos.command;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RegisterUserDto {
    public String firstName;
    public String lastName;
    public LocalDate birthDate;
    public String email;
    public String username;
    public String password;
}
