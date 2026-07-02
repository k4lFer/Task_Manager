package com.pck4x.task_manager.modules.board.objects.dtos.command;

import lombok.Getter;

@Getter
public class UpdateBoardDto {
    public String name;
    public String description;
    public Boolean isPrivate;
}
