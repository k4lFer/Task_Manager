package com.pck4x.task_manager.modules.chat.controllers;

import com.pck4x.task_manager.modules.chat.infrastructure.acl.PresenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class PresenceController {
/*
    private final PresenceService presenceService;

    @SubscribeMapping("/topic/online-users")
    public Set<String> handleSubscription(Principal principal) {
        return presenceService.getConnectedUsers();
    }
*/
}
