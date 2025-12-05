package com.pck4x.task_manager.modules.workspace.infrastructure.entities;

import com.pck4x.task_manager.modules.auth.infrastructure.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private UserEntity owner;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "workspace", fetch = FetchType.LAZY)
    private List<WorkspaceMembersEntity> workspaceMembers;
}
