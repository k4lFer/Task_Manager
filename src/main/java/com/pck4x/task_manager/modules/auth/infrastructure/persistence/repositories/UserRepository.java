package com.pck4x.task_manager.modules.auth.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.auth.domain.models.TUser;
import com.pck4x.task_manager.modules.auth.infrastructure.mapper.UserMapper;
import com.pck4x.task_manager.modules.auth.infrastructure.persistence.jpa.JpaUserRepository;
import com.pck4x.task_manager.modules.auth.interfaces.repositories.IUserRepository;
import com.pck4x.task_manager.modules.auth.objects.dtos.query.MyProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepository implements IUserRepository {
    private final UserMapper mapper;
    private final JpaUserRepository jpa;

    @Override
    public Optional<TUser> findById(UUID id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<TUser> findByUsername(String username) {
        return jpa.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    public Optional<TUser> findByEmail(String email) {
        return jpa.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<MyProfileDto> getMyProfile(UUID id) {
        return jpa.findById(id).map(mapper::toMyDto);
    }
}
