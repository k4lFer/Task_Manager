package com.pck4x.task_manager.modules.workspace.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "workspace_members", schema = "workspace")
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkspaceMembersEntity {
    @Id
    private UUID id;



    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private String createdAt;

}
