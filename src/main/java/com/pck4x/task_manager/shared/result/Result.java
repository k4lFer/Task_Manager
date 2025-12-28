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
    private HttpStatusCode status;
    private List<MessageDto> messages;

    public Result(T data, boolean isSuccess, HttpStatusCode httpStatus, List<MessageDto> messages) {
        this.data = data;
        this.isSuccess = isSuccess;
        this.status = httpStatus;
        this.messages = messages != null ? messages : Collections.emptyList();
    }

    // ---------- SUCCESS ----------

    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(
                data,
                true,
                HttpStatusCode.valueOf(200),
                List.of(new MessageDto(
                        "SUCCESS",
                        List.of(msg != null ? msg : "Operation successful")
                ))
        );
    }

    public static <T> Result<T> create(T data, String msg) {
        return new Result<>(
                data,
                true,
                HttpStatusCode.valueOf(201),
                List.of(new MessageDto(
                        "SUCCESS",
                        List.of(msg != null ? msg : "Resource created")
                ))
        );
    }

    public static <T> Result<T> noContent() {
        return new Result<>(
                null,
                true,
                HttpStatusCode.valueOf(204),
                Collections.emptyList()
        );
    }

    // ---------- SINGLE ERROR (casos puntuales) ----------

    public static <T> Result<T> error(String msg) {
        return new Result<>(
                null,
                false,
                HttpStatusCode.valueOf(400),
                List.of(new MessageDto(
                        "ERROR",
                        List.of(msg != null ? msg : "Bad request")
                ))
        );
    }

    public static <T> Result<T> forbidden(String msg) {
        return new Result<>(
                null,
                false,
                HttpStatusCode.valueOf(403),
                List.of(new MessageDto(
                        "FORBIDDEN",
                        List.of(msg != null ? msg : "Forbidden")
                ))
        );
    }

    public static <T> Result<T> conflict(String msg) {
        return new Result<>(
                null,
                false,
                HttpStatusCode.valueOf(409),
                List.of(new MessageDto(
                        "CONFLICT",
                        List.of(msg != null ? msg : "Conflict")
                ))
        );
    }

    public static <T> Result<T> notFound(String msg) {
        return new Result<>(
                null,
                false,
                HttpStatusCode.valueOf(404),
                List.of(new MessageDto(
                        "NOT_FOUND",
                        List.of(msg != null ? msg : "Resource not found")
                ))
        );
    }

    // ---------- MULTIPLE ERRORS ----------

    public static <T> Result<T> badRequest(List<String> errors) {
        return new Result<>(
                null,
                false,
                HttpStatusCode.valueOf(400),
                List.of(new MessageDto(
                        "ERROR",
                        errors != null ? errors : List.of("Bad request")
                ))
        );
    }

    // ---------- GENERIC FAIL (desde validators) ----------

    public static <T> Result<T> fail(HttpStatusCode status, List<MessageDto> messages) {
        return new Result<>(
                null,
                false,
                status,
                messages
        );
    }

    // ---------- EXCEPTION ----------

    public static <T> Result<T> exception(String msg) {
        return new Result<>(
                null,
                false,
                HttpStatusCode.valueOf(500),
                List.of(new MessageDto(
                        "EXCEPTION",
                        List.of(msg != null ? msg : "Internal server error")
                ))
        );
    }
}
