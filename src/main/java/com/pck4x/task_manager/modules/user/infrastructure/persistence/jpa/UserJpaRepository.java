package com.pck4x.task_manager.modules.user.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

}
