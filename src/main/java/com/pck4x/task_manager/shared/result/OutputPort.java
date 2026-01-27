package com.pck4x.task_manager.shared.result;

import com.pck4x.task_manager.shared.interfaces.IHttpResponse;
import com.pck4x.task_manager.shared.interfaces.IMessageDto;
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
public class OutputPort<T> implements IHttpResponse, IMessageDto {

    private T data = null;
    private boolean isSuccess;
    private HttpStatusCode status;
    private List<MessageDto> messages = Collections.emptyList();

    public OutputPort(T data, boolean isSuccess, HttpStatusCode httpStatus, List<MessageDto> messages) {
        this.data = data;
        this.isSuccess = isSuccess;
        this.status = httpStatus;
        this.messages = messages != null ? messages : Collections.emptyList();
    }

    @Override
    public HttpStatusCode getHttpStatusCode() {
        return status;
    }

    @Override
    public List<MessageDto> getMessage() {
        return messages;
    }

    public static <T> OutputPort<T> success(T data, HttpStatusCode statusCode, String message) {
        return new OutputPort<>(
                data,
                true,
                statusCode,
                Collections.singletonList(
                        new MessageDto("SUCCESS", message)
                )
        );
    }

    public static <T> OutputPort<T> failure(HttpStatusCode statusCode, String message) {
        return new OutputPort<>(
                null,
                false,
                statusCode,
                Collections.singletonList(
                        new MessageDto("ERROR", message)
                )
        );
    }

    public static <T> OutputPort<T> failures(HttpStatusCode statusCode, List<MessageDto> messages) {
        return new OutputPort<>(
                null,
                false,
                statusCode,
                messages
        );
    }
}