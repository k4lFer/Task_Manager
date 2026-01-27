package com.pck4x.task_manager.modules.auth.use_cases.command;

import com.pck4x.task_manager.shared.result.OutputPort;

public interface RefreshTokenCommand {
    OutputPort<String> execute(String input);
}
