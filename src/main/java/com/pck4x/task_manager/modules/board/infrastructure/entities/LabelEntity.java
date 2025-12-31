package com.pck4x.task_manager.modules.board.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "labels", schema = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LabelEntity {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private BoardEntity workspace;

    @Column(name = "created_at", columnDefinition = "timestamptz")
    private Instant createdAt;
}
