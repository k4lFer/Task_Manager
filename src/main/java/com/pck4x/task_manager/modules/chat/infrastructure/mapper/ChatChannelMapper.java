package com.pck4x.task_manager.modules.chat.infrastructure.mapper;

import com.pck4x.task_manager.modules.chat.domain.models.TChatChannel;
import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChatChannelEntity;
import com.pck4x.task_manager.modules.chat.objects.dtos.query.WorkspaceChannelDto;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class ChatChannelMapper {

    @Autowired
    protected EntityManager entityManager;

    @Mapping(target = "workspaceId", source = "workspace.id")
    public abstract TChatChannel toDomain(ChatChannelEntity entity);

    @Mapping(target = "workspace", source = "workspaceId", qualifiedByName = "mapWorkspaceRef")
    public abstract ChatChannelEntity toEntity(TChatChannel domain);

    @Named("mapWorkspaceRef")
    protected WorkspaceEntity mapWorkspaceRef(UUID workspaceId) {
        if (workspaceId == null)
            return null;
        return entityManager.getReference(WorkspaceEntity.class, workspaceId);
    }

    @Mapping(target = "channelId", source = "id")
    public abstract WorkspaceChannelDto toDto(ChatChannelEntity entity);
}
