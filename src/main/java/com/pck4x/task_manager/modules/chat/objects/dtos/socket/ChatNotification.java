package com.pck4x.task_manager.modules.chat.objects.dtos.socket;

public record ChatNotification<T>(
        NotificationType type,
        T payload
) {
    public enum NotificationType {
        CREATED,
        EDITED,
        DELETED
    }
}
