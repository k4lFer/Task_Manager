package com.pck4x.task_manager.modules.board.use_cases.listeners.domain;

import com.pck4x.task_manager.modules.board.domain.events.BoardCreatedEvent;
import com.pck4x.task_manager.modules.board.domain.models.TList;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BoardCreatedEventHandler {
    private final IListRepository listRepository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    void handle(BoardCreatedEvent event){
        var lists = List.of(
                TList.create(event.id(), "To Do", 1),
                TList.create(event.id(), "In progress", 2),
                TList.create(event.id(), "Done", 3)
        );
        listRepository.saveAll(lists, event.id());
    }
}
