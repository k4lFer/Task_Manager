package com.pck4x.task_manager.modules.workspace.use_cases.listeners.domain;

import com.pck4x.task_manager.modules.workspace.domain.events.WorkspaceInvitationAcceptedEvent;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class WorkspaceInvitationAcceptedEventHandler {
    private final IWorkspaceMemberRepository memberRepository;

    @TransactionalEventListener
    void handle(WorkspaceInvitationAcceptedEvent event){
        
    }
}
