package com.pck4x.task_manager.modules.task.objects.dtos.commands;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class CreateCardDto {
    public UUID listId;
    public String title;
    public String description;
    public Instant startDate;
    public Instant dueDate;
}
