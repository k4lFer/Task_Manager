package com.pck4x.task_manager.shared.domain.repository;

import java.util.ArrayList;
import java.util.List;

public abstract class TGenericDomain {
    protected final List<Object> domainEvents = new ArrayList<>();

    public List<Object> pullDomainEvents() {
        var events = List.copyOf(domainEvents);
        domainEvents.clear();
        return events;
    }
}
