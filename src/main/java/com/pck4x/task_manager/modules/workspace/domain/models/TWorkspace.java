package com.pck4x.task_manager.modules.workspace.domain.models;

import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceMemberRole;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class TWorkspace {

    private final UUID id;
    private final UUID ownerId;
    private final String name;
    private final String description;
    private final Boolean isPrivate;
    private final Instant createdAt;
    private Instant updatedAt;

    @Builder.Default
    private final List<TWorkspaceMembers> members = new ArrayList<>();

    private void touch() {
        this.updatedAt = Instant.now();
    }

    public static TWorkspace create(UUID ownerId, String name, String description, Boolean isPrivate) {
        var workspace = TWorkspace.builder()
                .id(UUID.randomUUID())
                .ownerId(ownerId)
                .name(name)
                .description(description)
                .isPrivate(isPrivate)
                .createdAt(Instant.now())
                .updatedAt(null)
                .build();

        workspace.addMember(ownerId, WorkspaceMemberRole.OWNER);

        return workspace;
    }

    public void addMember(UUID memberId, WorkspaceMemberRole role) {

        boolean exists = members.stream()
                .anyMatch(m -> m.getMemberId().equals(memberId));

        if (!exists) {
            members.add(TWorkspaceMembers.create(memberId, role));
            touch();
        }

    }

    public boolean removeMember(UUID memberId) {
        var removed = members.stream()
                .filter(m -> m.getMemberId().equals(memberId))
                .findFirst();

        if (removed.isEmpty()) return false;

        members.remove(removed.get());
        touch();
        return true;
    }
}
