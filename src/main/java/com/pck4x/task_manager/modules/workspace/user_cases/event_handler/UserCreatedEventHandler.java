package com.pck4x.task_manager.modules.workspace.user_cases.event_handler;

import com.pck4x.task_manager.modules.auth.use_cases.event.UserCreatedEvent;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspace;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreatedEventHandler {
    private final IWorkspaceRepository workspaceRepository;

    @ApplicationModuleListener
    public void on  (UserCreatedEvent event){
        var workspace = TWorkspace.create(
                event.id(),
                "Default Workspace",
                null,
                true
        );

        var workspaceMember = TWorkspaceMembers.create(
                workspace.getId(),
                event.id()
        );
        workspace.attachWorkspace(workspaceMember);

        workspaceRepository.create(workspace);
    }
}
