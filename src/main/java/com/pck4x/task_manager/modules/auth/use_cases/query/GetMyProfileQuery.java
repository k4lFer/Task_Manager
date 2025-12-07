package com.pck4x.task_manager.modules.auth.use_cases.query;


import com.pck4x.task_manager.modules.auth.objects.dtos.query.MyProfileDto;
import com.pck4x.task_manager.shared.result.Result;

import java.util.Optional;
import java.util.UUID;

public interface GetMyProfileQuery {
    Result<Optional<MyProfileDto>> execute(UUID id);
}
