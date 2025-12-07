package com.pck4x.task_manager.modules.workspace.infrastructure.mapper;

import com.pck4x.task_manager.modules.auth.infrastructure.entities.UserEntity;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import jakarta.persistence.EntityManager;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        uses = {WorkspaceMemberMapper.class}
)
public abstract class WorkspaceMapper {

    @Autowired
    protected EntityManager entityManager;


    @Mapping(target = "owner", source = "ownerId", qualifiedByName = "mapUserRef")
    @Mapping(target = "workspaceMember", source = "workspaceMember")
    public abstract WorkspaceEntity toEntity(TWorkspace domain);

    @Mapping(target = "ownerId", source = "owner.id")
    public abstract TWorkspace toDomain(WorkspaceEntity entity);

    /**
     * Convierte UUID a UserEntity (Proxy) sin hacer SELECT a la BD.
     */
    @Named("mapUserRef")
    protected UserEntity mapUserReference(UUID userId) {
        if (userId == null) return null;
        return entityManager.getReference(UserEntity.class, userId);
    }

    /**
     * Esto se ejecuta AUTOMÁTICAMENTE después de que MapStruct termina el mapeo principal.
     * Aquí vinculamos el padre (Workspace) a los hijos (Members).
     */
    @AfterMapping
    protected void linkMembers(@MappingTarget WorkspaceEntity entity) {
        if (entity.getWorkspaceMember() != null) {
            entity.getWorkspaceMember().forEach(member -> member.setWorkspace(entity));
        }
    }
}