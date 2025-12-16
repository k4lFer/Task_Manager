package com.pck4x.task_manager.modules.chat.use_cases.query;

import com.pck4x.task_manager.modules.chat.objects.dtos.query.ChatMessageResponseDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import com.pck4x.task_manager.shared.result.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface GetChannelMessagesQuery {
    Result<QueryResult<List<ChatMessageResponseDto>>> execute(UUID channelId, Pageable pageable);
}
