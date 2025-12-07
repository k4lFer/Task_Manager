package com.pck4x.task_manager.modules.chat.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.chat.domain.models.TChatChannel;
import com.pck4x.task_manager.modules.chat.infrastructure.mapper.ChatChannelMapper;
import com.pck4x.task_manager.modules.chat.infrastructure.persistence.jpa.JpaChatChannelRepository;
import com.pck4x.task_manager.modules.chat.interfaces.repositories.IChatChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatChannelRepository implements IChatChannelRepository {
    private final JpaChatChannelRepository jpa;
    private final ChatChannelMapper chatChannelMapper;

    @Override
    public TChatChannel save(TChatChannel chatChannel) {
        var channel = chatChannelMapper.toEntity(chatChannel);
        var saved = jpa.save(channel);
        return chatChannelMapper.toDomain(saved);
    }
}
