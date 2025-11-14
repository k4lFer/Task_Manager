package com.pck4x.task_manager.modules.auth.domain.repository;

import com.pck4x.task_manager.modules.auth.domain.models.TAuth;
import com.pck4x.task_manager.shared.domain.repository.IGenericRepository;

import java.util.UUID;

public interface IAuthRepository extends IGenericRepository<TAuth> {
    TAuth GetById(UUID id);
    TAuth GetByUsername(String username);
}
