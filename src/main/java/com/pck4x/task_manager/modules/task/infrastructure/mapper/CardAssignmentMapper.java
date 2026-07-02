package com.pck4x.task_manager.modules.task.infrastructure.mapper;

import com.pck4x.task_manager.modules.task.domain.TCardAssignments;
import com.pck4x.task_manager.modules.task.infrastructure.entities.CardAssignmentsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CardAssignmentMapper {

    @Mapping(target = "cardsId", source = "cards.id")
    public abstract TCardAssignments toDomain(CardAssignmentsEntity entity);

    @Mapping(target = "cards", ignore = true)
    public abstract CardAssignmentsEntity toEntity(TCardAssignments domain);
}
