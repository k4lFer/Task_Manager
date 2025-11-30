package com.pck4x.task_manager.shared.helper;

import com.pck4x.task_manager.shared.result.Result;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class ResponseHelper {

    public static <T> ResponseEntity<Result<T>> toResponse(Result<T> result) {
        int code = switch (result.getStatus()) {
            case "OK" -> 200;
            case "CREATED" -> 201;
            case "NO_CONTENT" -> 204;
            case "BAD_REQUEST" -> 400;
            case "NOT_FOUND" -> 404;
            case "CONFLICT" -> 409;
            case "INTERNAL_SERVER_ERROR" -> 500;
            default -> 200;
        };

        return switch (code) {
            case 201 -> ResponseEntity.created(URI.create("")).body(result);
            case 204 -> ResponseEntity.noContent().build();
            default -> ResponseEntity.status(code).body(result);
        };
    }
}
