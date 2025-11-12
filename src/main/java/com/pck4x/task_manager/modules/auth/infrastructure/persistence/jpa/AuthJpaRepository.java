package com.pck4x.task_manager.modules.auth.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.auth.infrastructure.persistence.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthJpaRepository extends JpaRepository<AuthEntity, UUID> {
}
