package com.gui.os.app;

import com.gui.os.app.input_output.OutputService;

public class DebugCommandService {
    private OutputService os;

    public DebugCommandService(OutputService os) {
        this.os = os;
    }

    public boolean execute(String command) {
        String[] data  = command.split(" ");

        if (data.length == 1 && data[0].equals("next")) {
            return true;
        } else if (data.length == 1 && data[0].equals("quit")) {
            os.clearForNewCommand();
            os.success(1, 3, "Quit debug mode.");
        } else {
            os.clearForNewCommand();
            os.error(1, 3, "Command not found. Type 'help' to find out about available commands.");
        }

        return false;
    }
}
