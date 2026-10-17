@org.springframework.modulith.ApplicationModule(
    type = org.springframework.modulith.ApplicationModule.Type.OPEN,
    allowedDependencies = {"shared::shared-api", "shared::shared-result", "shared::interfaces", "shared::domain.repository", "shared::application.adapter", "shared::objects"}
)
package com.pck4x.task_manager.modules.task;