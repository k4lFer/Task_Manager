package com.pck4x.task_manager.shared.helper;

import com.pck4x.task_manager.shared.objects.MessageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Schema(description = "Standard API response wrapper")
@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    @Schema(description = "Indicates if the operation was successful")
    private boolean success;
    @Schema(description = "Response payload")
    private T data;
    @Schema(description = "List of informational or error messages")
    private List<MessageDto> messages;
}