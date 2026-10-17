package com.pck4x.task_manager;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModulithArchitectureTest {

    @Test
    void verifyModuleBoundaries() {
        ApplicationModules.of(TaskManagerApplication.class).verify();
    }
}