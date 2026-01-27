package com.pck4x.task_manager.modules.auth.infrastructure.mapper;

import com.pck4x.task_manager.modules.auth.domain.models.TPerson;
import com.pck4x.task_manager.modules.auth.domain.models.TUser;
import com.pck4x.task_manager.modules.auth.infrastructure.entities.PersonEntity;
import com.pck4x.task_manager.modules.auth.infrastructure.entities.UserEntity;
import com.pck4x.task_manager.modules.auth.objects.dtos.query.MyProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface UserMapper {

    TUser toDomain(UserEntity entity);

    @Mapping(target = "person", source = "person")
    UserEntity toEntity(TUser domain);

    @Mapping(target = "user", ignore = true)
    PersonEntity toEntity(TPerson domain);

    @Mapping(target = "user", ignore = true)
    TPerson toDomain(PersonEntity entity);


    @Mapping(target = "userId", source = "id")
    @Mapping(target = "firstName", source = "person.firstName")
    @Mapping(target = "lastName", source = "person.lastName")
    @Mapping(target = "birthDate", source = "person.birthDate")
    MyProfileDto toMyDto(UserEntity user);
}
