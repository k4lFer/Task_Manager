package com.pck4x.task_manager.modules.board.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TList;
import com.pck4x.task_manager.modules.board.infrastructure.entities.ListEntity;
import com.pck4x.task_manager.modules.board.infrastructure.mapper.ListMapper;
import com.pck4x.task_manager.modules.board.infrastructure.persistence.jpa.JpaListRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ListRepository implements IListRepository {
    private final JpaListRepository jpa;
    private final ListMapper mapper;

    @Override
    public void saveAll(List<TList> lists) {
        List<ListEntity> entities = mapper.toEntityList(lists);

        jpa.saveAll(entities);
    }
}
