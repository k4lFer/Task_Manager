package com.pck4x.task_manager.modules.auth.infrastructure.persistence.jpa;

import com.pck4x.task_manager.modules.auth.infrastructure.entities.UserEntity;
import com.pck4x.task_manager.modules.auth.objects.dtos.output.SignInUserDto;
import com.pck4x.task_manager.modules.auth.objects.dtos.output.UserInfoOutDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);

    @Query("""
        SELECT new com.pck4x.task_manager.modules.auth.objects.dtos.output.SignInUserDto(
            u.id,
            u.username,
            u.person.firstName,
            u.person.lastName,
            u.password
        )
        FROM UserEntity u
        WHERE u.username = :username
    """)
    Optional<SignInUserDto> findSignInUserByUsername(@Param("username") String username);

    @Query("""
        SELECT new com.pck4x.task_manager.modules.auth.objects.dtos.output.UserInfoOutDto(
            u.id,
            u.email,
            u.person.firstName,
            u.person.lastName
        )
        FROM UserEntity u
        WHERE LOWER(u.email) = LOWER(:email)
    """)
    Optional<UserInfoOutDto> findInfoByExactEmail(@Param("email") String email);


    @Query("""
        SELECT new com.pck4x.task_manager.modules.auth.objects.dtos.output.UserInfoOutDto(
            u.id,
            u.email,
            u.person.firstName,
            u.person.lastName
        )
        FROM UserEntity u
        WHERE LOWER(u.email) LIKE LOWER(CONCAT('%', :emailPrefix, '%'))
    """)
    Page<UserInfoOutDto> searchByEmailPrefix(
            @Param("emailPrefix") String emailPrefix,
            Pageable pageable
    );

}
