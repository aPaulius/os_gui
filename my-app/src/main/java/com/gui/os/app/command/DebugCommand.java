package com.gui.os.app.command;

import com.gui.os.app.input_output.DebugInputService;
import com.gui.os.app.input_output.OutputService;
import java.io.IOException;

public class DebugCommand {
    private OutputService os;
    private DebugInputService dis;
    private MemoryCommand memoryCommand;
    private RegistersCommand registersCommand;

    public DebugCommand(OutputService os, DebugInputService dis, MemoryCommand memoryCommand, RegistersCommand registersCommand) {
        this.os = os;
        this.dis = dis;
        this.memoryCommand = memoryCommand;
        this.registersCommand = registersCommand;
    }

    public void execute() throws IOException {
        String[][] memory = memoryCommand.getMemory();

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                String command = memory[i][j];
                if (command != null) {
                    run(command);

                    if (nextCommand()) {
                        continue;
                    } else {
                        return;
                    }

                } else {
                    os.success(1, 3, "There is no commands in the memory.");
                    return;
                }
            }
        }
    }

    private void run(String command) {
        // TODO: this command is from memory. It should be executed here.
        // TODO: remove this after implementing command execute logic.
        registersCommand.generateRandomRegisterValues();

        os.clearForNewDebugCommand();
        registersCommand.printRegisters();
        os.info(1, 5, "Executed " + command + ".");
        memoryCommand.printMemory();
    }

    private boolean nextCommand() throws IOException {
        return dis.read();
    }
}
