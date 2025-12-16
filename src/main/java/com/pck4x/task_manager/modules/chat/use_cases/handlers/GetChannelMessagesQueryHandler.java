package com.pck4x.task_manager.modules.chat.use_cases.handlers;

import com.pck4x.task_manager.modules.chat.domain.models.TChatMessage;
import com.pck4x.task_manager.modules.chat.interfaces.repositories.IChatMessageRepository;
import com.pck4x.task_manager.modules.chat.objects.dtos.query.ChatMessageResponseDto;
import com.pck4x.task_manager.modules.chat.use_cases.query.GetChannelMessagesQuery;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import com.pck4x.task_manager.shared.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetChannelMessagesQueryHandler implements GetChannelMessagesQuery {
    private final IChatMessageRepository chatMessageRepository;

    @Override
    public Result<QueryResult<List<ChatMessageResponseDto>>> execute(UUID channelId, Pageable pageable) {
        var messagesPage = chatMessageRepository.findChatsByChannelId(channelId, pageable);

        return Result.success(messagesPage, "Messages retrieved successfully");
    }
}
