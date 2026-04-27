package com.pck4x.task_manager.modules.workspace.infrastructure.mapper;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = { WorkspaceMemberMapper.class }
)
public abstract class WorkspaceMapper {

    @Mapping(target = "members", source = "workspaceMember")
    public abstract TWorkspace toDomain(WorkspaceEntity entity);

    @Mapping(target = "workspaceMember", source = "members")
    @Mapping(target = "workspaceInvitation", ignore = true)
    public abstract WorkspaceEntity toEntity(TWorkspace domain);

    @AfterMapping
    protected void linkMembers(@MappingTarget WorkspaceEntity entity) {
        if (entity.getWorkspaceMember() == null) return;

        entity.getWorkspaceMember().forEach(m -> m.setWorkspace(entity));
    }
}