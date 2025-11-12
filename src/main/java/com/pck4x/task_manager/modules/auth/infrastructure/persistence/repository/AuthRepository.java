package com.pck4x.task_manager.modules.auth.infrastructure.persistence.repository;

import com.pck4x.task_manager.modules.auth.domain.models.TAuth;
import com.pck4x.task_manager.modules.auth.domain.repository.IAuthRepository;
import com.pck4x.task_manager.modules.auth.infrastructure.mapper.AuthInfraMapper;
import com.pck4x.task_manager.modules.auth.infrastructure.persistence.jpa.AuthJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Repository
@Transactional
public class AuthRepository implements IAuthRepository {
    private final AuthInfraMapper mapper;
    private final AuthJpaRepository jpa;

    @Override
    public TAuth Create(TAuth entity) {
        var auth = mapper.toPersistence(entity);
        var saved = jpa.save(auth);
        return mapper.toDomain(saved);
    }

    @Override
    public void Delete(UUID id) {

    }
}
