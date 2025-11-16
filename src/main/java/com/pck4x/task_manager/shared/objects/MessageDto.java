package com.pck4x.task_manager.shared.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MessageDto {
    private String type;
    private String messages;

    public MessageDto(String type, String message)
    {
        this.type = type;
        this.messages = message;
    }

    public MessageDto(String message)
    {
        this.messages = message;
    }

}
