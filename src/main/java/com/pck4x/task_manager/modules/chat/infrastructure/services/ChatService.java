package com.pck4x.task_manager.modules.chat.infrastructure.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pck4x.task_manager.modules.chat.interfaces.repositories.IChatMessageRepository;
import com.pck4x.task_manager.modules.chat.interfaces.services.IChatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService implements IChatService {
    private final IChatMessageRepository chatMessageRepository;

    @Override
    public String getSenderNameById(UUID senderId) {
        return chatMessageRepository.findSenderNameById(senderId);
    }
    
}
