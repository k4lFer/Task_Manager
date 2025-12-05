package com.pck4x.task_manager.modules.workspace.infrastructure.entities;

import com.pck4x.task_manager.modules.auth.infrastructure.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", referencedColumnName = "id")
    private WorkspaceEntity workspace;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private UserEntity member;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
