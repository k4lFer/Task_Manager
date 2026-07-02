package com.pck4x.task_manager.modules.task.infrastructure.mapper;

import com.pck4x.task_manager.modules.task.domain.TComments;
import com.pck4x.task_manager.modules.task.infrastructure.entities.CardEntity;
import com.pck4x.task_manager.modules.task.infrastructure.entities.CommentsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    @PersistenceContext
    protected EntityManager entityManager;

    @Mapping(target = "cardsId", source = "cards.id")
    public abstract TComments toDomain(CommentsEntity entity);

    @Mapping(target = "cards", expression = "java(mapCard(domain.getCardsId()))")
    public abstract CommentsEntity toEntity(TComments domain);

    protected CardEntity mapCard(UUID cardsId) {
        return entityManager.getReference(CardEntity.class, cardsId);
    }
}
