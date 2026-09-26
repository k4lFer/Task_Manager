package com.pck4x.task_manager.modules.board.interfaces.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TLabel;
import com.pck4x.task_manager.modules.board.domain.models.TList;

import java.util.List;
import java.util.UUID;

public interface IListRepository {
    TList save(TList list, UUID boardId);
    void saveAll(List<TList> lists, UUID boardId);
    List<UUID> findIdsByBoardId(UUID boardId);
    long countByBoardId(UUID boardId);
}
