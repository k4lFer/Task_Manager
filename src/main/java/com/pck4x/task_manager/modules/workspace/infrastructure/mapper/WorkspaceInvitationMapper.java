package com.pck4x.task_manager.modules.workspace.infrastructure.mapper;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceInvitation;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceInvitationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkspaceInvitationMapper {

    @Mapping(target = "workspace", ignore = true)
    WorkspaceInvitationEntity toEntity(TWorkspaceInvitation domain);

    @Mapping(target = "workspaceId", source = "workspace.id")
    TWorkspaceInvitation toDomain(WorkspaceInvitationEntity entity);
}
