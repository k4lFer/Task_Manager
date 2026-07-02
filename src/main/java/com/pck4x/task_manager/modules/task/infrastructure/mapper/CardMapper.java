package com.pck4x.task_manager.modules.task.infrastructure.mapper;

import com.pck4x.task_manager.modules.task.domain.TCard;
import com.pck4x.task_manager.modules.task.infrastructure.entities.CardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CardMapper {

    @Mapping(target = "completedAt", source = "completed_at")
    public abstract TCard toDomain(CardEntity entity);

    @Mapping(target = "completed_at", source = "completedAt")
    public abstract CardEntity toEntity(TCard domain);
}
