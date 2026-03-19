package com.pck4x.task_manager.modules.chat.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChannelEntity;
import com.pck4x.task_manager.modules.chat.objects.dtos.query.WorkspaceChannelDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface JpaChannelRepository extends JpaRepository<ChannelEntity, UUID> {

    @Query("SELECT new com.pck4x.task_manager.modules.chat.objects.dtos.query.WorkspaceChannelDto(" +
            "c.id, c.name, c.description) " +
            "FROM ChannelEntity c WHERE c.workspaceId = :workspaceId")
    Page<WorkspaceChannelDto> findByWorkspaceId(UUID workspaceId, Pageable pageable);
}
