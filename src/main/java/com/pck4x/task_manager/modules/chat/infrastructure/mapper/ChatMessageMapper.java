package com.pck4x.task_manager.modules.chat.infrastructure.mapper;

import com.pck4x.task_manager.modules.chat.domain.models.TChatMessage;
import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChatChannelEntity;
import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChatMessageEntity;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class ChatMessageMapper {

    @Autowired
    protected EntityManager entityManager;

    @Mapping(
            target = "chatChannel",
            source = "chatChannelId",
            qualifiedByName = "mapChannelRef"
    )
    public abstract ChatMessageEntity toEntity(TChatMessage domain);

    @Mapping(target = "chatChannelId", source = "chatChannel.id")
    public abstract TChatMessage toDomain(ChatMessageEntity entity);

    @Named("mapChannelRef")
    protected ChatChannelEntity mapChannelRef(UUID channelId) {
        return channelId == null
                ? null
                : entityManager.getReference(ChatChannelEntity.class, channelId);
    }
}
