package com.pck4x.task_manager.modules.board.interfaces.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TBoardMembers;

import java.util.Optional;
import java.util.UUID;

public interface IBoardMemberRepository {
    TBoardMembers save(TBoardMembers boardMembers);
    void deleteMember(UUID memberId);
    Optional<TBoardMembers> findByBoardIdAndMemberId(UUID boardId, UUID memberId);
}
