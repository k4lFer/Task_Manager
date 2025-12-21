package com.pck4x.task_manager.modules.workspace.infrastructure.mapper;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceMemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class WorkspaceMemberMapper {

    @Mapping(target = "workspaceId", source = "workspace.id")
    public abstract TWorkspaceMembers toDomain(WorkspaceMemberEntity entity);

    @Mapping(target = "workspace", ignore = true)
    public abstract WorkspaceMemberEntity toEntity(TWorkspaceMembers domain);
}