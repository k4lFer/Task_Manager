package com.pck4x.task_manager.modules.auth.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.auth.infrastructure.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaPersonRepository extends JpaRepository<PersonEntity, UUID> {
}
