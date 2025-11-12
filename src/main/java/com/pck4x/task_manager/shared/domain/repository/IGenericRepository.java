package com.pck4x.task_manager.shared.domain.repository;

import java.util.UUID;

public interface IGenericRepository<T> {
    T Create(T entity);
    void Delete(UUID id);
}
