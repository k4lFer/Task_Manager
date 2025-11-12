package com.pck4x.task_manager.shared.helper;

import com.pck4x.task_manager.shared.interfaces.IHttpResponse;
import com.pck4x.task_manager.shared.interfaces.IOutput;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class ResponseHelper {
    public static ResponseEntity<?> toResponse(IHttpResponse output){
        int code = output.getHttpStatusCode().value();

        return switch (code) {
            case 200 -> ResponseEntity.ok(output);
            case 201 -> ResponseEntity.created(URI.create("")).body(output);

            case 204 -> ResponseEntity.noContent().build();

            case 404 -> ResponseEntity.status(404).body(output);

            case 400 -> ResponseEntity.badRequest().body(output);

            case 409 -> ResponseEntity.status(409).body(output);

            default -> ResponseEntity.status(code).body(output);
        };
    }
}
