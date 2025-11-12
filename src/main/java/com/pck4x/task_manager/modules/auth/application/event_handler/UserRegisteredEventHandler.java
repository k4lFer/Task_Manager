package com.pck4x.task_manager.modules.auth.application.event_handler;

import com.pck4x.task_manager.modules.auth.domain.models.TAuth;
import com.pck4x.task_manager.modules.auth.domain.repository.IAuthRepository;
import com.pck4x.task_manager.modules.user.application.events.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRegisteredEventHandler {
    private final IAuthRepository authRepository;

    @ApplicationModuleListener
    public void on (UserRegisteredEvent event){
        var auth = TAuth.Create(
                event.username(),
                event.password(),
                event.id()
        );
        authRepository.Create(auth);
    }
}
