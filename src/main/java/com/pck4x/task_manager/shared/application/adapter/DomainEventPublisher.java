package com.pck4x.task_manager.shared.application.adapter;

import com.pck4x.task_manager.shared.domain.repository.TGenericDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DomainEventPublisher {
    private final ApplicationEventPublisher publisher;

    public void publishFrom(TGenericDomain aggregate) {
        aggregate.pullDomainEvents()
                .forEach(publisher::publishEvent);
    }
}
