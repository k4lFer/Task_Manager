package com.pck4x.task_manager.modules.task.objects.dtos.commands;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class UpdateCardDto {
    public String title;
    public String description;
    public Instant startDate;
    public Instant dueDate;
    public BigDecimal progress;
}
