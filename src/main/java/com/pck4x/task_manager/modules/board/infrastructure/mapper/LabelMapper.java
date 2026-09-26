package com.pck4x.task_manager.modules.board.infrastructure.mapper;

import com.pck4x.task_manager.modules.board.domain.models.TLabel;
import com.pck4x.task_manager.modules.board.infrastructure.entities.LabelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LabelMapper {
    TLabel toDomain(LabelEntity entity);
    LabelEntity toEntity(TLabel domain);
}
