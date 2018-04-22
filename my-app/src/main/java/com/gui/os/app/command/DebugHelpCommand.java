package com.gui.os.app.command;

import com.gui.os.app.input_output.OutputService;

public class DebugHelpCommand {
    private OutputService os;

    public DebugHelpCommand(OutputService os) {
        this.os = os;
    }

    public void execute() {
        os.clearForNewDebugCommand();
        os.info(1, 3, "next");
        os.info(2, 4, "quit");
    }
}
