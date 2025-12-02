package com.pck4x.task_manager.modules.workspace.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "workspaces", schema = "workspace")
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkspaceEntity {
    @Id
    private UUID id;




    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private String createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private String updatedAt;
}
