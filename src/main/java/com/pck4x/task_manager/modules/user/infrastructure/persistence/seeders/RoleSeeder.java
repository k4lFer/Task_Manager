package com.pck4x.task_manager.modules.user.infrastructure.persistence.seeders;

import com.pck4x.task_manager.modules.user.domain.enums.RoleName;
import com.pck4x.task_manager.modules.user.domain.repository.IRoleRepository;
import com.pck4x.task_manager.modules.user.infrastructure.persistence.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }
}
