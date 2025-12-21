package com.pck4x.task_manager.modules.auth.objects.dtos.output;

import java.util.UUID;

public record UserInfoOutDto(
        UUID id,
        String email,
        String firstName,
        String lastName
) {
}
