package com.pck4x.task_manager.modules.workspace.infrastructure.mapper;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import org.mapstruct.*;


@Mapper(
        componentModel = "spring"
)
public abstract class WorkspaceMapper {

    @Mapping(target = "workspaceMember", ignore = true)
    public abstract TWorkspace toDomain(WorkspaceEntity entity);

    public abstract WorkspaceEntity toEntity(TWorkspace domain);

    @AfterMapping
    protected void linkMembers(
            TWorkspace domain,
            @MappingTarget WorkspaceEntity entity
    ) {
        entity.getWorkspaceMember().forEach(m -> m.setWorkspace(entity));
    }
}
