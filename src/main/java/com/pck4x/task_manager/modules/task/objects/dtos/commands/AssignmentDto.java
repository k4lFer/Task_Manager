package com.pck4x.task_manager.modules.task.objects.dtos.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AssignmentDto {
    public UUID memberId;
    public UUID cardId;
}
