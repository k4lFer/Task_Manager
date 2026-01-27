package com.pck4x.task_manager.modules.auth.interfaces.repositories;

import com.pck4x.task_manager.modules.auth.domain.models.TPerson;

import java.util.Optional;
import java.util.UUID;

public interface IPersonRepository {
    Optional<TPerson> findById(UUID id);
}
