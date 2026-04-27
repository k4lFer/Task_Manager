package com.pck4x.task_manager.modules.workspace.use_cases.listeners.domain;

import com.pck4x.task_manager.modules.workspace.domain.events.WorkspaceInvitationAcceptedEvent;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceMemberRepository;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceRepository;
import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceMemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class WorkspaceInvitationAcceptedEventHandler {
    private final IWorkspaceMemberRepository memberRepository;
    private final IWorkspaceRepository workspaceRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void handle(WorkspaceInvitationAcceptedEvent event){
        var workspaceOpt = workspaceRepository.getWorkspace(event.workspaceId());

        var workspace = workspaceOpt.get();

        workspace.addMember(
                event.invitedUserId(),
                WorkspaceMemberRole.valueOf(event.role().name())
        );
        workspaceRepository.create(workspace);
    }
}
