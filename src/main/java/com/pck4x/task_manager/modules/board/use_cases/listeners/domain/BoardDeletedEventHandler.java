package com.pck4x.task_manager.modules.board.use_cases.listeners.domain;

import com.pck4x.task_manager.modules.board.domain.events.BoardDeletedEvent;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class BoardDeletedEventHandler {

    private final ICardRepository cardRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void handle(BoardDeletedEvent event) {
        var listIds = event.listIds();
        if (listIds != null && !listIds.isEmpty()) {
            cardRepository.deleteByListsIdIn(listIds);
        }
    }
}
