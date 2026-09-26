package com.pck4x.task_manager.modules.board.use_cases.handlers;

import com.pck4x.task_manager.modules.board.domain.models.TList;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardMemberRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IBoardRepository;
import com.pck4x.task_manager.modules.board.interfaces.repositories.IListRepository;
import com.pck4x.task_manager.modules.board.objects.dtos.command.CreateListDto;
import com.pck4x.task_manager.modules.board.use_cases.command.CreateListCommand;
import com.pck4x.task_manager.shared.result.OutputPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateListCommandHandler implements CreateListCommand {
    private final IBoardRepository boardRepository;
    private final IBoardMemberRepository boardMemberRepository;
    private final IListRepository listRepository;

    private static final int MAX_LISTS = 20;

    @Override
    public OutputPort<UUID> execute(UUID userId, UUID boardId, CreateListDto input) {
        var board = boardRepository.findById(boardId);
        if (board.isEmpty()) {
            return OutputPort.failure(HttpStatus.NOT_FOUND, "Board not found");
        }

        var membership = boardMemberRepository.findByBoardIdAndMemberId(boardId, userId);
        if (membership.isEmpty()) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "You are not a member of this board");
        }

        var role = membership.get().getRole();
        if (role == com.pck4x.task_manager.modules.board.objects.enums.BoardMemberRole.VIEWER) {
            return OutputPort.failure(HttpStatus.FORBIDDEN, "Viewers cannot create lists");
        }

        long count = listRepository.countByBoardId(boardId);
        if (count >= MAX_LISTS) {
            return OutputPort.failure(HttpStatus.BAD_REQUEST, "Maximum of " + MAX_LISTS + " lists reached");
        }

        var list = TList.create(boardId, input.getName(), (int) count);
        var saved = listRepository.save(list, boardId);

        if (saved != null) {
            return OutputPort.success(saved.getId(), HttpStatus.CREATED, "List created successfully");
        }

        return OutputPort.failure(HttpStatus.BAD_REQUEST, "Something went wrong");
    }
}
