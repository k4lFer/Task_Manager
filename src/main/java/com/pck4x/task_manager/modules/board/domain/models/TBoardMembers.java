package com.pck4x.task_manager.modules.board.domain.models;

import com.pck4x.task_manager.modules.board.objects.enums.BoardMemberRole;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class TBoardMembers {
    private UUID id;
    private UUID boardId;
    private UUID memberId;
    private BoardMemberRole role;
    private Instant createdAt;

    public static TBoardMembers create(UUID boardId, UUID memberId, BoardMemberRole role) {
        return TBoardMembers.builder()
                .id(UUID.randomUUID())
                .boardId(boardId)
                .memberId(memberId)
                .role(role)
                .build();
    }
}
