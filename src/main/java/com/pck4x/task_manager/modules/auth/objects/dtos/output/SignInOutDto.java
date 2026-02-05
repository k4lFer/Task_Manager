package com.pck4x.task_manager.modules.auth.objects.dtos.output;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record SignInOutDto(
        UUID id,
        String userName,
        String firstName,
        String lastName,
        String accessToken,

        @JsonIgnore
        String refreshToken
) {

}
