@org.springframework.modulith.ApplicationModule(
    type = org.springframework.modulith.ApplicationModule.Type.OPEN,
    allowedDependencies = {"shared", "shared::shared-api", "shared::shared-result", "shared::interfaces", "shared::objects"}
)
package com.pck4x.task_manager.modules.auth;