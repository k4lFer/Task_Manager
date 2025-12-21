package com.pck4x.task_manager.modules.workspace.use_cases.listeners.integration;

import com.pck4x.task_manager.modules.auth.use_cases.event.UserCreatedEvent;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class UserCreatedEventHandler {
    private final IWorkspaceRepository workspaceRepository;

    @TransactionalEventListener
    public void on(UserCreatedEvent event) {
        var workspace = TWorkspace.create(
                event.id(),
                "Default Workspace",
                "Workspace created automatically for new user",
                true);

        var workspaceMember = TWorkspaceMembers.create(
                workspace.getId(),
                event.id());
        workspace.attachWorkspace(workspaceMember);

        workspaceRepository.create(workspace);

    }
}
