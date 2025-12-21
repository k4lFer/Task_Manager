package com.pck4x.task_manager.modules.auth.interfaces.services;

import com.pck4x.task_manager.modules.auth.objects.dtos.output.UserInfoOutDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<UserInfoOutDto> getUserByExactEmail(String email);

    QueryResult<List<UserInfoOutDto>> searchByEmailPrefix(
            String emailPrefix,
            Pageable pageable
    );
}

