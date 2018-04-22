package com.gui.os.app.command;

import com.gui.os.app.input_output.OutputService;

public class HelpCommand {
    private OutputService os;

    public HelpCommand(OutputService os) {
        this.os = os;
    }

    public void execute() {
        os.clearForNewCommand();
        os.info(1, 3, "load [program]");
        os.info(1, 4, "run");
        os.info(1, 5, "memory");
        os.info(1, 6, "memory [block number]");
        os.info(1, 7, "registers");
        os.info(1, 8, "debug");
        os.info(3, 9, "next");
        os.info(3, 10, "quit");
    }
}
