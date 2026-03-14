package com.pck4x.task_manager.modules.chat.use_cases.handlers;

import com.pck4x.task_manager.modules.chat.interfaces.repositories.IChannelRepository;
import com.pck4x.task_manager.modules.chat.objects.dtos.query.WorkspaceChannelDto;
import com.pck4x.task_manager.modules.chat.use_cases.query.GetWorkspaceChannelsQuery;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetWorkspaceChannelsQueryHandler implements GetWorkspaceChannelsQuery {
    private final IChannelRepository chatChannelRepository;

    @Override
    public OutputPort<QueryResult<List<WorkspaceChannelDto>>> execute(UUID workspaceId, Pageable pageable) {
        var result = chatChannelRepository.getChannelsByWorkspaceId(workspaceId, pageable);

        if (result.getResults().isEmpty()) return OutputPort.success(null, HttpStatus.NO_CONTENT, null);

        return OutputPort.success(result, HttpStatus.OK,"Channels are ready");

    }
}
