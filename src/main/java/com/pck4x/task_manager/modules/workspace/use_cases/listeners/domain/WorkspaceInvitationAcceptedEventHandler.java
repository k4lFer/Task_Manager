package com.pck4x.task_manager.modules.workspace.use_cases.listeners.domain;

import com.pck4x.task_manager.modules.workspace.domain.events.WorkspaceInvitationAcceptedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class WorkspaceInvitationAcceptedEventHandler {

    @TransactionalEventListener
    void handle(WorkspaceInvitationAcceptedEvent event){

    }
}
