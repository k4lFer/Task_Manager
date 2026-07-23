package com.pck4x.task_manager.modules.chat.interfaces.repositories;

import com.pck4x.task_manager.modules.chat.domain.models.TChatMessage;
import com.pck4x.task_manager.modules.chat.objects.dtos.query.ChatMessageResponseDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IChatMessageRepository {
    TChatMessage save(TChatMessage chatMessage);

    Optional<TChatMessage> findById(UUID id);

    //Page<TChatMessage> findByChannelId(UUID channelId, Pageable pageable);

    QueryResult<List<ChatMessageResponseDto>> findChatsByChannelId(UUID channelId, Pageable pageable);

    String findSenderNameById(UUID userId);
}
