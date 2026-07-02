package com.pck4x.task_manager.modules.task.interfaces.repositories;

import com.pck4x.task_manager.modules.task.domain.TCardLabels;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICardLabelRepository {
    TCardLabels save(TCardLabels cardLabel);
    List<TCardLabels> findByCardId(UUID cardsId);
    Optional<TCardLabels> findByCardIdAndLabelId(UUID cardsId, UUID labelsId);
    void delete(TCardLabels cardLabel);
}
