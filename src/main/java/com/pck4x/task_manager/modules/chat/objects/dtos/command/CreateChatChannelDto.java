package com.pck4x.task_manager.modules.chat.objects.dtos.command;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateChatChannelDto {
    public UUID workspaceId;
    public String name;
    public String description = null;
}
