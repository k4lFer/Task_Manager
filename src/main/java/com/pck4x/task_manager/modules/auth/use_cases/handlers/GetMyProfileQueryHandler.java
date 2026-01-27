package com.pck4x.task_manager.modules.auth.use_cases.handlers;

import com.pck4x.task_manager.modules.auth.interfaces.repositories.IUserRepository;
import com.pck4x.task_manager.modules.auth.objects.dtos.query.MyProfileDto;
import com.pck4x.task_manager.modules.auth.use_cases.query.GetMyProfileQuery;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetMyProfileQueryHandler implements GetMyProfileQuery {
    private final IUserRepository userRepository;

    @Override
    public OutputPort<Optional<MyProfileDto>> execute(UUID id) {
        var user = userRepository.getMyProfile(id);
        if (user.isEmpty()) return OutputPort.failure(HttpStatus.BAD_REQUEST,"User Not Found");

        return OutputPort.success(user,  HttpStatus.OK,"Your profile is ready");
    }
}
