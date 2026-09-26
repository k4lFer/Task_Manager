package com.pck4x.task_manager.modules.board.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TLabel;
import com.pck4x.task_manager.modules.board.infrastructure.entities.LabelEntity;
import com.pck4x.task_manager.modules.board.infrastructure.mapper.LabelMapper;
import com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa.JpaBoardRepository;
import com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa.JpaLabelRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.ILabelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class LabelRepository implements ILabelRepository {
    private final JpaLabelRepository jpaLabelRepository;
    private final JpaBoardRepository boardJpa;
    private final LabelMapper labelMapper;

    @Override
    public TLabel save(TLabel domain) {
        var boardEntity = boardJpa.findById(domain.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found: " + domain.getBoardId()));

        LabelEntity entity = labelMapper.toEntity(domain);
        entity.setBoard(boardEntity);

        LabelEntity saved = jpaLabelRepository.save(entity);
        return labelMapper.toDomain(saved);
    }

    @Override
    public List<TLabel> findByBoardId(UUID boardId) {
        return jpaLabelRepository.findByBoardId(boardId)
                .stream()
                .map(labelMapper::toDomain)
                .toList();
    }
}
