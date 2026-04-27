package com.pck4x.task_manager.modules.task.infrastructure.mapper;

import com.pck4x.task_manager.modules.task.domain.TComments;
import com.pck4x.task_manager.modules.task.infrastructure.entities.CommentsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public abstract class CommentMapper {

    public abstract CommentsEntity toEntity(TComments domain);

    public abstract TComments toDomain(CommentsEntity entity);

}
