package com.pck4x.task_manager.modules.workspace.infrastructure.mapper;

import com.pck4x.task_manager.modules.auth.infrastructure.entities.PersonEntity;
import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChatChannelEntity;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceMemberEntity;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceChannelDto;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDetailDto;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDto;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceMemberDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WorkspaceQueryMapper {

    @Mapping(target = "ownerId", source = "entity.owner.id")
    @Mapping(target = "ownerName", expression = "java(concatName(entity.getOwner().getPerson()))")
    @Mapping(target = "members", source = "workspaceMember")
    @Mapping(target = "channels", source = "chatChannel")
    @Mapping(target = "isOwner", expression = "java(entity.getOwner().getId().equals(currentUserId))")
    WorkspaceDetailDto toDetailDto(WorkspaceEntity entity, @Context UUID currentUserId);

    @Mapping(target = "ownerId", source = "entity.owner.id")
    @Mapping(target = "ownerName", expression = "java(concatName(entity.getOwner().getPerson()))")
    @Mapping(target = "isOwner", expression = "java(entity.getOwner().getId().equals(currentUserId))")
    WorkspaceDto toDto(WorkspaceEntity entity, @Context UUID currentUserId);

    @Mapping(target = "userId", source = "entity.member.id")
    @Mapping(target = "fullName", expression = "java(concatName(entity.getMember().getPerson()))")
    @Mapping(target = "itsYou", expression = "java(entity.getMember().getId().equals(currentUserId))")
    WorkspaceMemberDto toMemberDto(WorkspaceMemberEntity entity, @Context UUID currentUserId);

    WorkspaceChannelDto toChannelDto(ChatChannelEntity channel);

    default String concatName(PersonEntity person) {
        if (person == null) return "Unknown";
        return person.getFirstName() + " " + person.getLastName();
    }
}