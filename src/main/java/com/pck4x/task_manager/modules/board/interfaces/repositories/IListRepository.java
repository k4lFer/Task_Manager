package com.pck4x.task_manager.modules.board.interfaces.repositories;

import com.pck4x.task_manager.modules.board.domain.models.TLabel;
import com.pck4x.task_manager.modules.board.domain.models.TList;

import java.util.List;

public interface IListRepository {
    void savAll(List<TList> lists);

}
