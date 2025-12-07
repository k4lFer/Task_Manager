package com.pck4x.task_manager.shared.interfaces;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class QueryResult<T> {
    private T results;
    private int totalCounts;
    private int totalPages;
    private int pageNumber;
    private int pageSize;

    private QueryResult(T results, int totalCounts, int totalPages, int pageNumber, int pageSize){
        this.results = results;
        this.totalCounts = totalCounts;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public static <T> QueryResult<T> success(T results, int totalCounts, int totalPages, int pageNumber, int pageSize){
        return new QueryResult<>(
                results,
                totalCounts,
                totalPages,
                pageNumber,
                pageSize
        );
    }
}
