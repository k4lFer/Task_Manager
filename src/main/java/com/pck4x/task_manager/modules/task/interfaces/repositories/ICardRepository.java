package com.pck4x.task_manager.modules.task.interfaces.repositories;

import com.pck4x.task_manager.modules.task.domain.TCard;

import java.util.UUID;

public interface ICardRepository {
    TCard create(TCard card);
    Integer getNextPosition(UUID listId);
}
