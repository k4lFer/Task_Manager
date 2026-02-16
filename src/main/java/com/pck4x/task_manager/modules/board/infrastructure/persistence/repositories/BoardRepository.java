package com.pck4x.task_manager.modules.board.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TBoard;
import com.pck4x.task_manager.modules.board.infrastructure.entities.BoardEntity;
import com.pck4x.task_manager.modules.board.infrastructure.mapper.BoardMapper;
import com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa.JpaBoardRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository implements IBoardRepository {
    private final JpaBoardRepository jpa;
    private final BoardMapper mapper;

    @Override
    @Transactional
    public TBoard save(TBoard board) {
        BoardEntity entity = mapper.toEntity(board);

        BoardEntity saved = jpa.save(entity);

        return mapper.toDomain(saved);
    }
}
