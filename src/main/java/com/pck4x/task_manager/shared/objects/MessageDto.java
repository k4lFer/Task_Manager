package com.pck4x.task_manager.shared.objects;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Informational or error message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    @Schema(description = "Message type (e.g. SUCCESS, ERROR)", example = "SUCCESS")
    private String type;
    @Schema(description = "Message content", example = "Operation completed successfully")
    private String messages;
}