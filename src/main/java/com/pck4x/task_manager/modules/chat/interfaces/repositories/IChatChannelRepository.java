package com.pck4x.task_manager.modules.chat.interfaces.repositories;

import com.pck4x.task_manager.modules.chat.domain.models.TChatChannel;

public interface IChatChannelRepository {
    TChatChannel save(TChatChannel chatChannel);
}
