package com.pck4x.task_manager.modules.board.interfaces.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TLabel;

import java.util.List;
import java.util.UUID;

public interface ILabelRepository {
    TLabel save(TLabel label);
    List<TLabel> findByBoardId(UUID boardId);
}
