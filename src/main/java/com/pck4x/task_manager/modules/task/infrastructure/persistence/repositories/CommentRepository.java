package com.pck4x.task_manager.modules.task.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.task.domain.TComments;
import com.pck4x.task_manager.modules.task.infrastructure.mapper.CommentMapper;
import com.pck4x.task_manager.modules.task.infrastructure.persistence.jpa.JpaCommentsRepository;
import com.pck4x.task_manager.modules.task.interfaces.repositories.ICommentRepository;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CommentDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CommentRepository implements ICommentRepository {
    private final JpaCommentsRepository jpa;
    private final CommentMapper mapper;

    @Override
    @Transactional
    public TComments save(TComments comment) {
        var entity = mapper.toEntity(comment);
        var saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<TComments> findById(UUID id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<TComments> findByCardId(UUID cardsId) {
        return jpa.findByCardsIdOrderByCreatedAtAsc(cardsId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void delete(TComments comment) {
        jpa.deleteById(comment.getId());
    }

    @Override
    public List<CommentDto> findCommentDtosByCardId(UUID cardsId) {
        return jpa.findCommentDtosByCardId(cardsId);
    }
}
