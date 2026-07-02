package com.pck4x.task_manager.modules.task.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.task.infrastructure.entities.CommentsEntity;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaCommentsRepository extends JpaRepository<CommentsEntity, UUID> {
    List<CommentsEntity> findByCardsIdOrderByCreatedAtAsc(UUID cardsId);

    @Query("""
        SELECT new com.pck4x.task_manager.modules.task.objects.dtos.query.CommentDto(
            cm.id,
            cm.cards.id,
            cm.userId,
            CONCAT(p.firstName, ' ', p.lastName),
            cm.content,
            cm.createdAt,
            cm.updatedAt
        )
        FROM CommentsEntity cm
        JOIN UserEntity u ON u.id = cm.userId
        JOIN u.person p
        WHERE cm.cards.id = :cardsId
        ORDER BY cm.createdAt ASC
    """)
    List<CommentDto> findCommentDtosByCardId(@Param("cardsId") UUID cardsId);
}
