package com.pck4x.task_manager.modules.chat.interfaces.repositories;

import com.pck4x.task_manager.modules.chat.domain.models.TChatChannel;
import com.pck4x.task_manager.modules.chat.objects.dtos.query.WorkspaceChannelDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IChatChannelRepository {
    TChatChannel save(TChatChannel chatChannel);
    boolean channelExits(UUID id);
    Optional<TChatChannel> findById(UUID id);
    QueryResult<List<WorkspaceChannelDto>> getChannelsByWorkspaceId(UUID workspaceId, Pageable pageable);
}
