package com.pck4x.task_manager.modules.task.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cards", schema = "task")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CardEntity {
    @Id
    private UUID id;

    @Column(name = "lists_id", nullable = false)
    private UUID listsId;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT", nullable = true)
    private String description;

    @Column(name = "position")
    private Integer position;

    @Column(name = "start_date", columnDefinition = "timestamptz", nullable = true)
    private Instant startDate;

    @Column(name = "due_date", columnDefinition = "timestamptz", nullable = true)
    private Instant dueDate;

    @Column(name = "completed_at", columnDefinition = "timestamptz", nullable = true)
    private Instant completed_at;

    @Column(name = "progress", precision = 5, scale = 2)
    private BigDecimal progress;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = true)
    private Instant updatedAt;


    @OneToMany(mappedBy = "cards", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardLabelsEntity> cardLabels = new ArrayList<>();

    @OneToMany(mappedBy = "cards", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardAssignmentsEntity> cardAssignments = new ArrayList<>();

    @OneToMany(mappedBy = "cards", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentsEntity> comments = new ArrayList<>();

}
