package com.pck4x.task_manager.modules.chat.use_cases.query;

import com.pck4x.task_manager.modules.chat.objects.dtos.query.WorkspaceChannelDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import com.pck4x.task_manager.shared.result.OutputPort;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface GetWorkspaceChannelsQuery {
    OutputPort<QueryResult<List<WorkspaceChannelDto>>> execute(UUID workspaceId, Pageable pageable);

}
