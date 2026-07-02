package com.pck4x.task_manager.modules.board.interfaces.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TLabel;
import com.pck4x.task_manager.modules.board.domain.models.TList;

import java.util.List;
import java.util.UUID;

public interface IListRepository {
    void saveAll(List<TList> lists);
    List<UUID> findIdsByBoardId(UUID boardId);
}
