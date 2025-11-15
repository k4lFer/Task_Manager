package com.pck4x.task_manager.modules.user.domain.models;

import com.pck4x.task_manager.modules.user.domain.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TRole {
    private UUID id;
    private RoleName role;

}
