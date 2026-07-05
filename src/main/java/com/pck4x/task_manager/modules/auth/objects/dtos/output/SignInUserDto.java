package com.pck4x.task_manager.modules.auth.objects.dtos.output;

import java.util.UUID;

public record SignInUserDto(
        UUID id,
        String username,
        String firstName,
        String lastName,
        String password
) {
}
