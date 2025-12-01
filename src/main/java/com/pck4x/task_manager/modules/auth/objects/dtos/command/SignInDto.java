package com.pck4x.task_manager.modules.auth.objects.dtos.command;

import lombok.Getter;

@Getter
public class SignInDto {
    public String username;
    public String password;
}
