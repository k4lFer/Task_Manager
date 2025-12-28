package com.pck4x.task_manager.shared.helper;

import com.pck4x.task_manager.shared.objects.MessageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private List<MessageDto> messages;
}