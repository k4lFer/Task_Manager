package com.pck4x.task_manager.modules.auth.objects.dtos.output;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SignInOutDto {
    public UUID id;
    public String userName;
    public String accessToken;

    @JsonIgnore
    private String refreshToken;

}
