package com.pck4x.task_manager.modules.board.infrastructure.entities;

import com.pck4x.task_manager.modules.board.objects.enums.BoardMemberRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "board_members", schema = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BoardMemberEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private BoardEntity workspace;

    @Column(name = "member_id", nullable = false)
    private UUID memberId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private BoardMemberRole role;

    @Column(name = "created_at", columnDefinition = "timestamptz")
    private Instant createdAt;

}
