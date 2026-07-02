package com.pck4x.task_manager.modules.auth.infrastructure.services;

import com.pck4x.task_manager.modules.auth.interfaces.repositories.IUserRepository;
import com.pck4x.task_manager.modules.auth.interfaces.services.IUserService;
import com.pck4x.task_manager.modules.auth.objects.dtos.output.UserInfoOutDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public Optional<UserInfoOutDto> getUserByExactEmail(String email) {
        return userRepository.findInfoByExactEmail(email);
    }

    @Override
    public Optional<UserInfoOutDto> getUserById(UUID id) {
        return userRepository.findById(id)
                .map(user -> new UserInfoOutDto(
                        user.getId(),
                        user.getEmail(),
                        user.getPerson().getFirstName(),
                        user.getPerson().getLastName()
                ));
    }

    @Override
    public QueryResult<List<UserInfoOutDto>> searchByEmailPrefix(
            String emailPrefix,
            Pageable pageable
    ) {
        return userRepository.searchByEmailPrefix(emailPrefix, pageable);
    }
}
