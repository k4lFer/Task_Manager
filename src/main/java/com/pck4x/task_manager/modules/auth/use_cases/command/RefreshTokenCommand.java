package com.pck4x.task_manager.modules.auth.use_cases.command;

import com.pck4x.task_manager.shared.result.Result;

public interface RefreshTokenCommand {
    Result<String> execute(String input);
}
