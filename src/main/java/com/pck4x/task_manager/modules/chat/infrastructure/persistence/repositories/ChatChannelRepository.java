package com.pck4x.task_manager.modules.chat.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.chat.domain.models.TChatChannel;
import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChatChannelEntity;
import com.pck4x.task_manager.modules.chat.infrastructure.mapper.ChatChannelMapper;
import com.pck4x.task_manager.modules.chat.infrastructure.persistence.jpa.JpaChatChannelRepository;
import com.pck4x.task_manager.modules.chat.interfaces.repositories.IChatChannelRepository;
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
public class ChatChannelRepository implements IChatChannelRepository {
    private final JpaChatChannelRepository jpa;
    private final ChatChannelMapper chatChannelMapper;

    @Override
    public TChatChannel save(TChatChannel chatChannel) {
        ChatChannelEntity channel = chatChannelMapper.toEntity(chatChannel);
        ChatChannelEntity saved = jpa.save(channel);
        return chatChannelMapper.toDomain(saved);

    }

    @Override
    public Optional<TChatChannel> findById(UUID id) {
       return jpa.findById(id).map(chatChannelMapper::toDomain);
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
