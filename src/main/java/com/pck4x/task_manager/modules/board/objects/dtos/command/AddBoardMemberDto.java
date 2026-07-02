package com.pck4x.task_manager.modules.board.objects.dtos.command;

import com.pck4x.task_manager.modules.board.objects.enums.BoardMemberRole;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AddBoardMemberDto {
    public UUID userId;
    public BoardMemberRole role;
}
