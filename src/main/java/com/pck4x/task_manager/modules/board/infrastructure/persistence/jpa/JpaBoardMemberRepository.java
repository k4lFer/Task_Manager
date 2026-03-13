package com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.board.infrastructure.entities.BoardMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaBoardMemberRepository extends JpaRepository<BoardMemberEntity, UUID> {
    Optional<BoardMemberEntity> findByBoardIdAndMemberId(UUID boardId, UUID memberId);
}
