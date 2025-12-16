package com.pck4x.task_manager.modules.chat.objects.dtos.command;

import lombok.Getter;

import java.util.UUID;

@Getter
public class EditMessageDto {
    public UUID messageId;
    public String newMessage;
}
