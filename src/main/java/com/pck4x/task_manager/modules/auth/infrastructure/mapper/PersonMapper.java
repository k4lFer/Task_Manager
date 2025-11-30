package com.pck4x.task_manager.modules.auth.infrastructure.mapper;

import com.pck4x.task_manager.modules.auth.domain.models.TPerson;
import com.pck4x.task_manager.modules.auth.infrastructure.entities.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(target = "user", ignore = true)
    TPerson toDomain(PersonEntity entity);

    @Mapping(target = "user", ignore = true)
    PersonEntity toEntity(TPerson domain);
}
