package com.pck4x.task_manager.modules.board.infrastructure.mapper;

import com.pck4x.task_manager.modules.board.domain.models.TList;
import com.pck4x.task_manager.modules.board.infrastructure.entities.ListEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ListMapper {

    @Mapping(target = "boardId", source = "board.id")
    public abstract TList toDomain(ListEntity entity);

    @Mapping(target = "board", ignore = true)
    public abstract ListEntity toEntity(TList domain);

    public abstract List<TList> toDomainList(List<ListEntity> entities);

    public abstract List<ListEntity> toEntityList(List<TList> domains);
}
