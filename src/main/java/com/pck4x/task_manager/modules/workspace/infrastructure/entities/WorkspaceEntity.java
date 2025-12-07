package com.pck4x.task_manager.modules.workspace.infrastructure.entities;

import com.pck4x.task_manager.modules.auth.infrastructure.entities.UserEntity;
import com.pck4x.task_manager.modules.chat.infrastructure.entities.ChatChannelEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workspaces", schema = "workspace")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkspaceEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @Column(name = "created_at", columnDefinition = "timestamptz")
    private Instant createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamptz")
    private Instant updatedAt;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkspaceMemberEntity> workspaceMember = new ArrayList<>();

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatChannelEntity> chatChannel = new ArrayList<>();
}
