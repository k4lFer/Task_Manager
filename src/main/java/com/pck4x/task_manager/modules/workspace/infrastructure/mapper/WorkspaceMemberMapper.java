package com.pck4x.task_manager.modules.workspace.infrastructure.mapper;

import com.pck4x.task_manager.modules.auth.infrastructure.entities.UserEntity;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceMemberEntity;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class WorkspaceMemberMapper {

    @Autowired
    protected EntityManager entityManager;

    @Mapping(target = "workspaceId", source = "workspace.id")
    @Mapping(target = "workspace", ignore = true) // <--- ESTO ROMPE EL BUCLE
    public abstract TWorkspaceMembers toDomain(WorkspaceMemberEntity entity);

    // Mapeo inverso (Entity)
    @Mapping(target = "workspace", ignore = true) // El padre lo setea el WorkspaceMapper con @AfterMapping
    @Mapping(target = "member", source = "memberId", qualifiedByName = "mapMemberRef")
    public abstract WorkspaceMemberEntity toEntity(TWorkspaceMembers domain);

    @org.mapstruct.Named("mapMemberRef")
    protected UserEntity mapMemberReference(UUID memberId) {
        if (memberId == null) return null;
        return entityManager.getReference(UserEntity.class, memberId);
    }
}