package com.pck4x.task_manager.modules.board.objects.dtos.command;

import lombok.Getter;

@Getter
public class CreateBoardDto {
    public String name;
    public String description = null;
    public Boolean isPrivate = true;
}
