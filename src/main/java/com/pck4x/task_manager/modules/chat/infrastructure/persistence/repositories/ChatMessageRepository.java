package com.pck4x.task_manager.modules.chat.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.chat.domain.models.TChatMessage;
import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChatMessageEntity;
import com.pck4x.task_manager.modules.chat.infrastructure.mapper.ChatMessageMapper;
import com.pck4x.task_manager.modules.chat.infrastructure.persistence.jpa.JpaChatMessageRepository;
import com.pck4x.task_manager.modules.chat.interfaces.repositories.IChatMessageRepository;
import com.pck4x.task_manager.modules.chat.objects.dtos.query.ChatMessageResponseDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ChatMessageRepository implements IChatMessageRepository {
    private final JpaChatMessageRepository jpa;
    private final ChatMessageMapper mapper;

    @Override
    public TChatMessage save(TChatMessage chatMessage) {
        var entity = mapper.toEntity(chatMessage);
        var saved = jpa.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Optional<TChatMessage> findById(UUID id) {
       return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public QueryResult<List<ChatMessageResponseDto>> findChatsByChannelId(UUID channelId, Pageable pageable) {
        int pageIndex = Math.max(pageable.getPageNumber(), 0);

        Pageable pageRequest = PageRequest.of(pageIndex, pageable.getPageSize());

        var result = jpa.findByChatChannelId(channelId, pageRequest);

        return QueryResult.success(
                result.getContent(),
                (int) result.getTotalElements(),
                result.getTotalPages(),
                result.getNumber(),
                result.getSize()
        );
    }
}
