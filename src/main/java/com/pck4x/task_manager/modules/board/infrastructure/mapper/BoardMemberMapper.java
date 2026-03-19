package com.pck4x.task_manager.modules.board.infrastructure.mapper;

import com.pck4x.task_manager.modules.board.domain.models.TBoardMembers;
import com.pck4x.task_manager.modules.board.infrastructure.entities.BoardEntity;
import com.pck4x.task_manager.modules.board.infrastructure.entities.BoardMemberEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class BoardMemberMapper {

    @PersistenceContext
    protected EntityManager entityManager;

    @Mapping(target = "boardId", source = "board.id")
    public abstract TBoardMembers toDomain(BoardMemberEntity entity);

    @Mapping(target = "board", expression = "java(mapBoard(domain.getBoardId()))")
    public abstract BoardMemberEntity toEntity(TBoardMembers domain);

    protected BoardEntity mapBoard(UUID boardId) {
        return entityManager.getReference(BoardEntity.class, boardId);
    }
}
