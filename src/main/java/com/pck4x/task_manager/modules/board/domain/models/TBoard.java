package com.pck4x.task_manager.modules.board.domain.models;

import com.pck4x.task_manager.modules.board.domain.events.BoardCreatedEvent;
import com.pck4x.task_manager.shared.domain.repository.TGenericDomain;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class TBoard extends TGenericDomain {
    private UUID id;
    private UUID workspaceId;
    private UUID ownerId;
    private String name;
    private String description;
    private Boolean isPrivate;
    private Instant createdAt;
    private Instant updatedAt;

    @Builder.Default
    private List<TBoardMembers> boardMembers = new java.util.ArrayList<>();

    public static TBoard create(UUID workspaceId, UUID ownerId, String name, String description, Boolean isPrivate) {
        return TBoard.builder()
                .id(UUID.randomUUID())
                .workspaceId(workspaceId)
                .ownerId(ownerId)
                .name(name)
                .description(description)
                .isPrivate(isPrivate)
                .build();
    }


    public void attachBoard(TBoardMembers boardMember) {
        this.boardMembers.add(boardMember);
        domainEvents.add(new BoardCreatedEvent(this.id));
    }
}
