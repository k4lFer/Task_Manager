package com.pck4x.task_manager.modules.user.infrastructure.persistence.repository;

import com.pck4x.task_manager.modules.user.domain.models.TRole;
import com.pck4x.task_manager.modules.user.domain.repository.IRoleRepository;

import java.util.UUID;

public class RoleRepository implements IRoleRepository {
    @Override
    public TRole Create(TRole entity) {
        return null;
    }

    @Override
    public void Delete(UUID id) {

    }
}
