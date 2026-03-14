package com.pck4x.task_manager.modules.chat.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.chat.domain.models.TChannel;
import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChannelEntity;
import com.pck4x.task_manager.modules.chat.infrastructure.mapper.ChannelMapper;
import com.pck4x.task_manager.modules.chat.infrastructure.persistence.jpa.JpaChannelRepository;
import com.pck4x.task_manager.modules.chat.interfaces.repositories.IChannelRepository;
import com.pck4x.task_manager.modules.chat.objects.dtos.query.WorkspaceChannelDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ChannelRepository implements IChannelRepository {
    private final JpaChannelRepository jpa;
    private final ChannelMapper channelMapper;

    @Override
    public TChannel save(TChannel chatChannel) {
        ChannelEntity channel = channelMapper.toEntity(chatChannel);
        ChannelEntity saved = jpa.save(channel);
        return channelMapper.toDomain(saved);

    }

    @Override
    public Optional<TChannel> findById(UUID id) {
       return jpa.findById(id).map(channelMapper::toDomain);
    }

    @Override
    public QueryResult<List<WorkspaceChannelDto>> getChannelsByWorkspaceId(UUID workspaceId, Pageable pageable) {
        var page = jpa.findByWorkspaceId(workspaceId, pageable);

        return QueryResult.success(
                page.getContent(),
                (int) page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize());
    }

    @Override
    public boolean channelExits(UUID id) {
        return jpa.existsById(id);
    }
}
