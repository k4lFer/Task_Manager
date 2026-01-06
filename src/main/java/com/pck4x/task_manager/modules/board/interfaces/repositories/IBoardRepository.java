package com.pck4x.task_manager.modules.board.interfaces.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TBoard;

public interface IBoardRepository {
    TBoard save(TBoard board);
}
