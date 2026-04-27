package com.pck4x.task_manager.modules.task.infrastructure.mapper;

import com.pck4x.task_manager.modules.task.domain.TCard;
import com.pck4x.task_manager.modules.task.infrastructure.entities.CardEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public abstract class CardMapper {

    public abstract TCard toDomain(CardEntity entity);

    public abstract CardEntity toEntity(TCard domain);
}
