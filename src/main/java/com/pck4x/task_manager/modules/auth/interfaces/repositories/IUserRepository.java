package com.pck4x.task_manager.modules.auth.interfaces.repositories;

import com.pck4x.task_manager.modules.auth.domain.models.TUser;
import com.pck4x.task_manager.modules.auth.objects.dtos.output.UserInfoOutDto;
import com.pck4x.task_manager.modules.auth.objects.dtos.query.MyProfileDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    Optional<TUser> findById(UUID id);
    Optional<TUser> findByUsername(String username);
    Optional<TUser> findByEmail(String email);
    QueryResult<List<UserInfoOutDto>> searchByEmailPrefix(String email, Pageable pageable);
    Optional<UserInfoOutDto> findInfoByExactEmail(String email);
    Optional<MyProfileDto> getMyProfile(UUID id);
    java.util.List<TUser> findAllById(java.util.List<UUID> ids);
}
