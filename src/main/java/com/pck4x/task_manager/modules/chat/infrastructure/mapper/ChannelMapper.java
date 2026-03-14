package com.pck4x.task_manager.modules.chat.infrastructure.mapper;

import com.pck4x.task_manager.modules.chat.domain.models.TChannel;
import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChannelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChannelMapper {

    TChannel toDomain(ChannelEntity entity);

    ChannelEntity toEntity(TChannel domain);
}

