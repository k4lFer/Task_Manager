package com.pck4x.task_manager.modules.chat.objects.dtos.socket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatNotification {
    private NotificationType type;
    private Object payload;

    public enum NotificationType {
        CREATED,
        EDITED,
        DELETED
    }
}
