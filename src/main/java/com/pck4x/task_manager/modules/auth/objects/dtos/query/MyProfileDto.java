package com.pck4x.task_manager.modules.auth.objects.dtos.query;

import java.util.Date;
import java.util.UUID;

public record MyProfileDto(
        UUID userId,
        String username,
        String email,
        String firstName,
        String lastName,
        Date birthDate
) {}