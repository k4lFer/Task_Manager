package com.pck4x.task_manager.shared.result;

import com.pck4x.task_manager.shared.objects.MessageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Result<T> {
    private T data;
    private boolean isSuccess;
    private String status;             // "OK", "CREATED", etc.
    private List<MessageDto> messages;

    public Result(T data, boolean isSuccess, HttpStatusCode httpStatus, List<MessageDto> messages) {
        this.data = data;
        this.isSuccess = isSuccess;
        this.status = httpStatus.toString();
        this.messages = messages != null ? messages : Collections.emptyList();
    }

    // -------------------- Helpers --------------------
    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(data, true, HttpStatusCode.valueOf(200),
                List.of(new MessageDto("success", msg != null ? msg : "Resource retrieved successfully")));
    }

    public static <T> Result<T> create(T data, String msg) {
        return new Result<>(data, true, HttpStatusCode.valueOf(201),
                List.of(new MessageDto("success", msg != null ? msg : "Resource created successfully")));
    }

    public static <T> Result<T> noContent() {
        return new Result<>(null, true, HttpStatusCode.valueOf(204), Collections.emptyList());
    }

    public static <T> Result<T> forbidden(String msg) {
        return new Result<>(null, false, HttpStatusCode.valueOf(403),
                List.of(new MessageDto("forbidden", msg != null ? msg : "Forbidden")));
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(null, false, HttpStatusCode.valueOf(400),
                List.of(new MessageDto("error", msg != null ? msg : "Bad request")));
    }

    public static <T> Result<T> notFound(String msg) {
        return new Result<>(null, false, HttpStatusCode.valueOf(404),
                List.of(new MessageDto("not_found", msg != null ? msg : "Resource not found")));
    }

    public static <T> Result<T> conflict(String msg) {
        return new Result<>(null, false, HttpStatusCode.valueOf(409),
                List.of(new MessageDto("conflict", msg != null ? msg : "Conflict")));
    }

    public static <T> Result<T> exception(String msg) {
        return new Result<>(null, false, HttpStatusCode.valueOf(500),
                List.of(new MessageDto("exception", msg != null ? msg : "Internal server error")));
    }
    public static <T> Result<T> failures(List<String> msgs) {
        List<MessageDto> messages = msgs.stream()
                .map(msg -> new MessageDto("error", msg))
                .toList(); // Java 16+ o usar collect(Collectors.toList())
        return new Result<>(null, false, HttpStatusCode.valueOf(400), messages);
    }
}
