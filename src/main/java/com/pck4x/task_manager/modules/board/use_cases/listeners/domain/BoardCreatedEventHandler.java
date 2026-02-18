package com.pck4x.task_manager.modules.board.use_cases.listeners.domain;

import com.pck4x.task_manager.modules.board.domain.events.BoardCreatedEvent;
import com.pck4x.task_manager.modules.board.domain.models.TList;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardCreatedEventHandler {
    private final IListRepository listRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void handle(BoardCreatedEvent event){
        var lists = List.of(
                TList.create(event.id(), "To Do", 1),
                TList.create(event.id(), "In progress",2),
                TList.create(event.id(), "Done",3)
        );
        listRepository.savAll(lists);
    }
}
