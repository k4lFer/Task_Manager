package com.pck4x.task_manager.modules.board.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TList;
import com.pck4x.task_manager.modules.board.infrastructure.entities.ListEntity;
import com.pck4x.task_manager.modules.board.infrastructure.mapper.ListMapper;
import com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa.JpaBoardRepository;
import com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa.JpaListRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ListRepository implements IListRepository {
    private final JpaListRepository jpa;
    private final JpaBoardRepository boardJpa;
    private final ListMapper mapper;

    @Override
    public TList save(TList list, UUID boardId) {
        var boardEntity = boardJpa.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found: " + boardId));
        var entity = mapper.toEntity(list);
        entity.setBoard(boardEntity);
        var saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void saveAll(List<TList> lists, UUID boardId) {
        var boardEntity = boardJpa.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found: " + boardId));

        List<ListEntity> entities = mapper.toEntityList(lists);
        entities.forEach(e -> e.setBoard(boardEntity));
        jpa.saveAll(entities);
    }

    @Override
    public List<UUID> findIdsByBoardId(UUID boardId) {
        return jpa.findIdsByBoardId(boardId);
    }

    @Override
    public long countByBoardId(UUID boardId) {
        return jpa.countByBoardId(boardId);
    }
}
