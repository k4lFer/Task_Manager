package com.pck4x.task_manager.modules.auth.infrastructure.entities;

import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceEntity;
import com.pck4x.task_manager.modules.workspace.infrastructure.entities.WorkspaceMembersEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "auth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserEntity {
    @Id
    private UUID id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false, unique = true)
    private PersonEntity person;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<WorkspaceEntity> workspaces;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<WorkspaceMembersEntity> workspaceMembers;
}
