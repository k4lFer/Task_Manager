package com.pck4x.task_manager.modules.task.infrastructure.mapper;

import com.pck4x.task_manager.modules.task.domain.TCardLabels;
import com.pck4x.task_manager.modules.task.infrastructure.entities.CardLabelsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CardLabelMapper {

    @Mapping(target = "cardsId", source = "cards.id")
    public abstract TCardLabels toDomain(CardLabelsEntity entity);

    @Mapping(target = "cards", ignore = true)
    public abstract CardLabelsEntity toEntity(TCardLabels domain);
}
