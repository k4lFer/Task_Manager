package com.pck4x.task_manager.modules.workspace.infrastructure.mapper;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import org.mapstruct.*;


@Mapper(
        componentModel = "spring",
        uses = {WorkspaceMemberMapper.class}
)
public abstract class WorkspaceMapper {

    @Mapping(target = "workspaceMember", source = "workspaceMember")
    public abstract WorkspaceEntity toEntity(TWorkspace domain);

    @Mapping(target = "workspaceMember", ignore = true)
    public abstract TWorkspace toDomain(WorkspaceEntity entity);

    @AfterMapping
    protected void linkMembers(@MappingTarget WorkspaceEntity entity) {
        if (entity.getWorkspaceMember() != null) {
            entity.getWorkspaceMember()
                    .forEach(member -> member.setWorkspace(entity));
        }
    }
}
