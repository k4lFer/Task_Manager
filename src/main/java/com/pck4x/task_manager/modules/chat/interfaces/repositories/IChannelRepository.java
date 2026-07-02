package com.pck4x.task_manager.modules.chat.interfaces.repositories;

import com.pck4x.task_manager.modules.chat.domain.models.TChannel;
import com.pck4x.task_manager.modules.chat.objects.dtos.query.WorkspaceChannelDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IChannelRepository {
    TChannel save(TChannel chatChannel);
    boolean channelExits(UUID id);
    Optional<TChannel> findById(UUID id);
    void deleteById(UUID id);
    boolean existsByNameAndWorkspaceId(UUID workspaceId, String name);
    QueryResult<List<WorkspaceChannelDto>> getChannelsByWorkspaceId(UUID workspaceId, Pageable pageable);
}
