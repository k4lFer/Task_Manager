package com.pck4x.task_manager.modules.workspace.domain.events;

import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceInvitationRole;
import org.jmolecules.event.annotation.DomainEvent;

import java.util.UUID;

@DomainEvent
public record WorkspaceInvitationAcceptedEvent(
        UUID invitedUserId,
        WorkspaceInvitationRole role
) {
}
