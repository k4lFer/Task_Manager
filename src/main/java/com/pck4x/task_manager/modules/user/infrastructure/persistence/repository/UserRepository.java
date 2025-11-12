package com.pck4x.task_manager.modules.user.infrastructure.persistence.repository;

import com.pck4x.task_manager.modules.user.domain.models.TUser;
import com.pck4x.task_manager.modules.user.domain.repository.IUserRepository;
import com.pck4x.task_manager.modules.user.infrastructure.mapper.UserInfraMapper;
import com.pck4x.task_manager.modules.user.infrastructure.persistence.jpa.UserJpaRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Repository
@Transactional
public class UserRepository implements IUserRepository {
    private final UserInfraMapper mapper;
    private final UserJpaRepository jpa;

    @Override
    public TUser Create(TUser entity) {
        var user = mapper.toPersistence(entity);
        var saved = jpa.save(user);
        return mapper.toDomain(saved);
    }

    @Override
    public void Delete(UUID id) {
        jpa.deleteById(id);
    }
}
