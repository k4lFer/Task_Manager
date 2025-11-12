package com.pck4x.task_manager.shared.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MessageDto {
    public String type;
    public String messages;

    public MessageDto(String type, String message)
    {
        this.type = type;
        this.messages = message;
    }

}
