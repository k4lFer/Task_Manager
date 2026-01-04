package com.pck4x.task_manager.modules.workspace.infrastructure.mapper;

import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceMemberEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class WorkspaceMemberMapper {

    @PersistenceContext
    protected EntityManager entityManager;

    @Mapping(target = "workspaceId", source = "workspace.id")
    public abstract TWorkspaceMembers toDomain(WorkspaceMemberEntity entity);

    @Mapping(target = "workspace", expression = "java(mapWorkspace(domain.getWorkspaceId()))")
    public abstract WorkspaceMemberEntity toEntity(TWorkspaceMembers domain);

    protected WorkspaceEntity mapWorkspace(UUID workspaceId) {
        return entityManager.getReference(WorkspaceEntity.class, workspaceId);
    }
}