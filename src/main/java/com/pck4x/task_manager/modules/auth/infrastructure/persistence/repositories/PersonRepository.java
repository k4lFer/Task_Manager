package com.pck4x.task_manager.modules.auth.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.auth.domain.models.TPerson;
import com.pck4x.task_manager.modules.auth.infrastructure.mapper.PersonMapper;
import com.pck4x.task_manager.modules.auth.infrastructure.persistence.jpa.JpaPersonRepository;
import com.pck4x.task_manager.modules.auth.interfaces.repositories.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PersonRepository implements IPersonRepository {
    private final PersonMapper personMapper;
    private final JpaPersonRepository jpa;

    @Override
    public Optional<TPerson> findById(UUID id) {
        return jpa.findById(id).map(personMapper::toDomain);
    }
}
