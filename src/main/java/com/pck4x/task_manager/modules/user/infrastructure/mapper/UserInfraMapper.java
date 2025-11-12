package com.pck4x.task_manager.modules.user.infrastructure.mapper;

import com.pck4x.task_manager.modules.user.domain.models.TUser;
import com.pck4x.task_manager.modules.user.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInfraMapper {
    UserEntity toPersistence(TUser user);
    TUser toDomain(UserEntity user);
}
