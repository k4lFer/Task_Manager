package com.pck4x.task_manager.modules.board.use_cases.handlers;

import com.pck4x.task_manager.modules.board.domain.models.TBoard;
import com.pck4x.task_manager.modules.board.domain.models.TBoardMembers;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.objects.dtos.command.CreateBoardDto;
import com.pck4x.task_manager.modules.board.objects.enums.BoardMemberRole;
import com.pck4x.task_manager.modules.board.use_cases.command.CreateBoardCommand;
import com.pck4x.task_manager.shared.application.adapter.DomainEventPublisher;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateBoardCommandHandler implements CreateBoardCommand {
    private final IBoardRepository boardRepository;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public OutputPort<UUID> execute(UUID userId, UUID workspaceId, CreateBoardDto input) {

        var board = TBoard.create(
                workspaceId,
                userId,
                input.getName(),
                input.getDescription(),
                input.getIsPrivate()
        );

        var boardMember = TBoardMembers.create(
                board.getId(),
                userId,
                BoardMemberRole.OWNER
        );

        board.attachBoard(boardMember);

        var saved = boardRepository.save(board);
        if (saved == null)  return OutputPort.failure(HttpStatus.BAD_REQUEST, "Unable to create board");

        domainEventPublisher.publishFrom(board);

        return OutputPort.success(saved.getId(), HttpStatus.CREATED, "Board created successfully");

    }
}
