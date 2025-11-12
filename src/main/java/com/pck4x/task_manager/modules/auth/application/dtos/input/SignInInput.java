package com.pck4x.task_manager.modules.auth.application.dtos.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInInput {
    public String userName;
    public String password;

}
