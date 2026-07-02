package com.pck4x.task_manager.modules.task.interfaces.repositories;

import com.pck4x.task_manager.modules.task.domain.TComments;
import com.pck4x.task_manager.modules.task.objects.dtos.query.CommentDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICommentRepository {
    TComments save(TComments comment);
    Optional<TComments> findById(UUID id);
    List<TComments> findByCardId(UUID cardsId);
    void delete(TComments comment);
    List<CommentDto> findCommentDtosByCardId(UUID cardsId);
}
