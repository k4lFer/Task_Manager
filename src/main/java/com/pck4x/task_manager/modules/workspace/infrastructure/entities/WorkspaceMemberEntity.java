package com.pck4x.task_manager.modules.workspace.infrastructure.entities;

import com.pck4x.task_manager.modules.workspace.objects.enums.WorkspaceMemberRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "workspace_members", schema = "workspace")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkspaceMemberEntity {
    
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", nullable = false)
    private WorkspaceEntity workspace;


    @Column(name = "member_id", nullable = false)
    private UUID memberId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private WorkspaceMemberRole role;

    @Column(name = "created_at", columnDefinition = "timestamptz")
    private Instant createdAt;

}
