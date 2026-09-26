package com.pck4x.task_manager.modules.board.objects.dtos.command;

import lombok.Data;

@Data
public class CreateLabelDto {
    private String name;
    private String color;
}
