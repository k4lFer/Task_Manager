package com.pck4x.task_manager.shared.application.interfaces;

public interface IHandler <TI, TResponse> {
    // CompletableFuture<TResponse> Execute(TInput input);
    TResponse Execute(TI input);
}
