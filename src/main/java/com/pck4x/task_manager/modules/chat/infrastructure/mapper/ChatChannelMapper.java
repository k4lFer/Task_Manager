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
public interface ChatChannelMapper {

    TChatChannel toDomain(ChatChannelEntity entity);

    ChatChannelEntity toEntity(TChatChannel domain);
}

