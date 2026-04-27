package com.pck4x.task_manager.modules.workspace.use_cases.listeners.integration;

import com.pck4x.task_manager.modules.auth.use_cases.event.UserCreatedEvent;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceMemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class UserCreatedEventHandler {
    private final IWorkspaceRepository workspaceRepository;

    @ApplicationModuleListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void on(UserCreatedEvent event) {
        var workspace = TWorkspace.create(
                event.id(),
                "Default Workspace",
                "Workspace created automatically for new user",
                true
        );

        workspaceRepository.create(workspace);

    }
}
