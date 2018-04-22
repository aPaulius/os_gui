package com.gui.os.app;

import com.gui.os.app.command.*;
import com.gui.os.app.input_output.OutputService;
import java.io.IOException;

public class CommandService {
    private HelpCommand helpCommand;
    private LoadCommand loadCommand;
    private RunCommand runCommand;
    private MemoryCommand memoryCommand;
    private RegistersCommand registersCommand;
    private DebugCommand debugCommand;
    private OutputService os;

    public CommandService(
            HelpCommand helpCommand,
            LoadCommand loadCommand,
            RunCommand runCommand,
            MemoryCommand memoryCommand,
            RegistersCommand registersCommand,
            DebugCommand debugCommand,
            OutputService os
    ) {
        this.helpCommand = helpCommand;
        this.loadCommand = loadCommand;
        this.runCommand = runCommand;
        this.memoryCommand = memoryCommand;
        this.registersCommand = registersCommand;
        this.debugCommand = debugCommand;
        this.os = os;
    }

    public void execute(String command) throws IOException {
        String[] data = command.split(" ");

        if (data.length == 1 && data[0].equals("help")) {
            helpCommand.execute();
        } else if (data.length == 2 && data[0].equals("load")) {
            loadCommand.execute(data[1]);
        } else if (data.length == 1 && data[0].equals("run")) {
            runCommand.execute();
        } else if (data.length == 1 && data[0].equals("memory")) {
            memoryCommand.execute();
        } else if (data.length == 2 && data[0].equals("memory")) {
            memoryCommand.execute(data[1]);
        } else if (data.length == 1 && data[0].equals("registers")) {
            registersCommand.execute();
        } else if (data.length == 1 && data[0].equals("debug")) {
            debugCommand.execute();
        } else {
            os.error(1, 3, "Command not found. Type 'help' to find out about available commands.");
        }
    }
}
