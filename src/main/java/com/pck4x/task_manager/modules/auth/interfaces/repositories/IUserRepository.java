package com.pck4x.task_manager.modules.auth.interfaces.repositories;

import com.pck4x.task_manager.modules.auth.domain.models.TUser;
import com.pck4x.task_manager.modules.auth.objects.dtos.query.MyProfileDto;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    Optional<TUser> findById(UUID id);
    Optional<TUser> findByUsername(String username);
    Optional<TUser> findByEmail(String email);
    Optional<MyProfileDto> getMyProfile(UUID id);
}
