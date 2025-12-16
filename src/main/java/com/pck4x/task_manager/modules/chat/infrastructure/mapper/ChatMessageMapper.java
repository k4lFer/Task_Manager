package com.pck4x.task_manager.modules.chat.infrastructure.mapper;

import com.pck4x.task_manager.modules.auth.infrastructure.entities.UserEntity;
import com.pck4x.task_manager.modules.chat.domain.models.TChatMessage;
import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChatChannelEntity;
import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChatMessageEntity;
import com.pck4x.task_manager.modules.chat.objects.dtos.query.ChatMessageResponseDto;
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


    @Mapping(target = "chatChannel", source = "chatChannelId", qualifiedByName = "mapChannelRef")
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUserRef")
    public abstract ChatMessageEntity toEntity(TChatMessage domain);

    @Mapping(target = "chatChannelId", source = "chatChannel.id")
    @Mapping(target = "userId", source = "user.id")
    public abstract TChatMessage toDomain(ChatMessageEntity entity);


    @Mapping(target = "channelId", source = "chatChannel.id")
    @Mapping(target = "senderId", source = "user.id")
    @Mapping(
            target = "senderName",
            expression = """
            java(
                entity.getUser() != null && entity.getUser().getPerson() != null
                    ? entity.getUser().getPerson().getFirstName() + " " + entity.getUser().getPerson().getLastName()
                    : entity.getUser() != null
                        ? entity.getUser().getUsername()
                        : "Unknown"
            )
        """
    )
    @Mapping(target = "isEdited", expression = "java(entity.getEditedAt() != null)")
    public abstract ChatMessageResponseDto toDto(ChatMessageEntity entity);


    @Named("mapChannelRef")
    protected ChatChannelEntity mapChannelRef(UUID channelId) {
        if (channelId == null) return null;
        return entityManager.getReference(ChatChannelEntity.class, channelId);
    }

    @Named("mapUserRef")
    protected UserEntity mapUserRef(UUID userId) {
        if (userId == null) return null;
        return entityManager.getReference(UserEntity.class, userId);
    }
}
