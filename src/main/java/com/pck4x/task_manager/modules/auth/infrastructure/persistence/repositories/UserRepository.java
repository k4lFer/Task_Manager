package com.pck4x.task_manager.modules.auth.infrastructure.persistence.repositories;

import com.pck4x.task_manager.modules.auth.domain.models.TUser;
import com.pck4x.task_manager.modules.auth.infrastructure.mapper.UserMapper;
import com.pck4x.task_manager.modules.auth.infrastructure.persistence.jpa.JpaUserRepository;
import com.pck4x.task_manager.modules.auth.interfaces.repositories.IUserRepository;
import com.pck4x.task_manager.modules.auth.objects.dtos.output.UserInfoOutDto;
import com.pck4x.task_manager.modules.auth.objects.dtos.query.MyProfileDto;
import com.pck4x.task_manager.shared.interfaces.QueryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.PageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepository implements IUserRepository {
    private final UserMapper mapper;
    private final JpaUserRepository jpa;

    @Override
    public Optional<TUser> findById(UUID id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<TUser> findByUsername(String username) {
        return jpa.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    public Optional<TUser> findByEmail(String email) {
        return jpa.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public QueryResult<List<UserInfoOutDto>> searchByEmailPrefix(String email, Pageable pageable) {
        int pageIndex = Math.max(pageable.getPageNumber(), 0);
        Pageable pageRequest = PageRequest.of(pageIndex, pageable.getPageSize());

        Page<UserInfoOutDto> result = jpa.searchByEmailPrefix(email, pageRequest);
        return QueryResult.success(
                result.getContent(),
                (int) result.getTotalElements(),
                result.getTotalPages(),
                result.getNumber(),
                result.getSize()
        );
    }

    @Override
    public Optional<UserInfoOutDto> findInfoByExactEmail(String email) {
        return jpa.findInfoByExactEmail(email);
    }

    @Override
    public Optional<MyProfileDto> getMyProfile(UUID id) {
        return jpa.findById(id).map(mapper::toMyDto);
    }

    @Override
    public List<TUser> findAllById(java.util.List<UUID> ids) {
        return jpa.findAllById(ids).stream().map(mapper::toDomain).toList();
    }
}
