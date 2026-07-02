package com.pck4x.task_manager.modules.board.use_cases.listeners.domain;

import com.pck4x.task_manager.modules.board.domain.events.BoardUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class BoardUpdatedEventHandler {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void handle(BoardUpdatedEvent event) {
    }
}
