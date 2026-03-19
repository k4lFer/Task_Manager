package com.pck4x.task_manager.modules.task.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "card_assignments", schema = "task")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CardAssignmentsEntity {
    @Id
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cards_id")
    private CardEntity cards;

    @Column(name = "assigned_at", columnDefinition = "timestamptz")
    private Instant assignedAt;


}
