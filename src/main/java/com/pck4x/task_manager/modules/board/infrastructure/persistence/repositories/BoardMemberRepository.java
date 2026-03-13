package com.pck4x.task_manager.modules.board.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TBoardMembers;
import com.pck4x.task_manager.modules.board.infrastructure.mapper.BoardMemberMapper;
import com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa.JpaBoardMemberRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BoardMemberRepository implements IBoardMemberRepository {
    private final JpaBoardMemberRepository jpaBoardMemberRepository;
    private final BoardMemberMapper boardMemberMapper;

    @Override
    public TBoardMembers save(TBoardMembers boardMembers) {
        var entity = boardMemberMapper.toEntity(boardMembers);
        var saved = jpaBoardMemberRepository.save(entity);
        return boardMemberMapper.toDomain(saved);
    }

    @Override
    public void deleteMember(UUID memberId) {

    }

    @Override
    public Optional<TBoardMembers> findByBoardIdAndMemberId(UUID boardId, UUID memberId) {
        return jpaBoardMemberRepository.findByBoardIdAndMemberId(boardId, memberId)
                .map(boardMemberMapper::toDomain);
    }
}
