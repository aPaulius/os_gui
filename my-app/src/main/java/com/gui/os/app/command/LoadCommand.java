package com.gui.os.app.command;

import com.gui.os.app.input_output.OutputService;
import java.util.ArrayList;

public class LoadCommand {
    private OutputService os;
    private MemoryCommand memoryCommand;
    private ArrayList<String> programs;

    public LoadCommand(OutputService os, MemoryCommand memoryCommand) {
        this.os = os;
        this.memoryCommand = memoryCommand;
        this.programs = new ArrayList<String>();
    }

    public void execute(String program) {
        boolean isLoaded = load(program);

        if (isLoaded) {
            programs.add(program);
            os.success(1, 3, "Program " + program + " loaded.");
        } else {
            os.error(1, 3, "Failed to load " + program + ".");
        }
    }

    public String getLastProgram() {
        if (programs.size() > 0) {
            return programs.remove(programs.size() - 1);
        } else {
            return null;
        }
    }

    private boolean load(String program) {
        // TODO: implement load command logic.
        // TODO: remove after implement load command logic.
        memoryCommand.generateRandomMemory();
        return true;
    }
}
