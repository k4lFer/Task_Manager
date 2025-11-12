package com.pck4x.task_manager.modules.auth.infrastructure.mapper;

import com.pck4x.task_manager.modules.auth.domain.models.TAuth;
import com.pck4x.task_manager.modules.auth.infrastructure.persistence.entity.AuthEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthInfraMapper {
    AuthEntity toPersistence(TAuth auth);
    TAuth toDomain(AuthEntity auth);
}
