package com.pck4x.task_manager.modules.chat.infrastructure.mapper;

import com.pck4x.task_manager.modules.chat.domain.models.TChatChannel;
import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChatChannelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatChannelMapper {

    TChatChannel toDomain(ChatChannelEntity entity);

    ChatChannelEntity toEntity(TChatChannel domain);
}
