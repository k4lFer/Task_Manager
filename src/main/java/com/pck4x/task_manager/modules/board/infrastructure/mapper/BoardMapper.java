package com.pck4x.task_manager.modules.board.infrastructure.mapper;

import com.pck4x.task_manager.modules.board.domain.models.TBoard;
import com.pck4x.task_manager.modules.board.infrastructure.entities.BoardEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class BoardMapper {

    @Mapping(target = "boardMembers", ignore = true)
    public abstract TBoard toDomain(BoardEntity entity);

    public abstract BoardEntity toEntity(TBoard domain);

    @AfterMapping
    protected void LinkMembers(
            TBoard domain,
            @MappingTarget BoardEntity entity
    ){
        entity.getBoardMembers().forEach(m -> m.setBoard(entity));
    }
}
