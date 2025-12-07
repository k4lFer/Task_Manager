package com.pck4x.task_manager.modules.workspace.infrastructure.mapper;

import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceMemberEntity;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceDetailDto;
import com.pck4x.task_manager.modules.workspace.objects.dtos.query.WorkspaceMemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkspaceQueryMapper {

    @Mapping(target = "ownerId", source = "owner.id")
    @Mapping(target = "ownerEmail", source = "owner.email")
    @Mapping(target = "ownerName", expression = "java(entity.getOwner().getPerson().getFirstName() + ' ' + entity.getOwner().getPerson().getLastName())")
    @Mapping(target = "members", source = "workspaceMember")
    WorkspaceDetailDto toDetailDto(WorkspaceEntity entity);

    @Mapping(target = "userId", source = "member.id")
    @Mapping(target = "email", source = "member.email")
    @Mapping(target = "fullName", expression = "java(entity.getMember().getPerson().getFirstName() + ' ' + entity.getMember().getPerson().getLastName())")
    WorkspaceMemberDto toMemberDto(WorkspaceMemberEntity entity);
}