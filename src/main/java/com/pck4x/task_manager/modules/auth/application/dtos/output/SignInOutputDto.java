package com.pck4x.task_manager.modules.auth.application.dtos.output;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class SignInOutputDto {
    private UUID id;
    private String accessToken;
    private String refreshToken;
}
