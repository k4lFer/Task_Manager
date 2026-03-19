package com.pck4x.task_manager.modules.task.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "card_labels", schema = "task")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CardLabelsEntity {
    @Id
    private UUID id;

    @Column(name = "labels_id")
    private UUID labelsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cards_id")
    private CardEntity cards;
}
