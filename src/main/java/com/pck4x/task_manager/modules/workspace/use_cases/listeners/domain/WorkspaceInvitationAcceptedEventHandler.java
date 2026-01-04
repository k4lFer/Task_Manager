package com.pck4x.task_manager.modules.workspace.use_cases.listeners.domain;

import com.pck4x.task_manager.modules.workspace.domain.events.WorkspaceInvitationAcceptedEvent;
import com.pck4x.task_manager.modules.workspace.domain.models.TWorkspaceMembers;
import com.pck4x.task_manager.modules.workspace.interfaces.repositories.IWorkspaceMemberRepository;
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

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void handle(WorkspaceInvitationAcceptedEvent event){
        var member = TWorkspaceMembers.create(
                event.workspaceId(),
                event.invitedUserId(),
                WorkspaceMemberRole.valueOf(event.role().name()),
                event.respondedAt()
        );

        memberRepository.save(member);

    }
}
