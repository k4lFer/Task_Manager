package com.pck4x.task_manager.modules.board.objects.dtos.command;

import com.pck4x.task_manager.modules.board.objects.enums.BoardMemberRole;

import lombok.Getter;

@Getter
public class UpdateBoardMemberDto {
    private BoardMemberRole role;
}
