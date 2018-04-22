package com.gui.os.app.command;

import com.gui.os.app.input_output.OutputService;

public class RunCommand {
    private LoadCommand loadCommand;
    private OutputService os;
    private RegistersCommand registersCommand;

    public RunCommand(LoadCommand loadCommand, OutputService os, RegistersCommand registersCommand) {
        this.loadCommand = loadCommand;
        this.os = os;
        this.registersCommand = registersCommand;
    }

    public void execute() {
        String lastProgram = loadCommand.getLastProgram();

        if (lastProgram == null) {
            os.error(1, 3, "There is no loaded programs.");
            return;
        }

        boolean isRunSuccessful = run(lastProgram);

        if (isRunSuccessful) {
            os.success(1, 3, "Program " + lastProgram + " run successfully.");
        } else {
            os.error(1, 3, "Failed to run " + lastProgram + " program.");
        }
    }

    private boolean run(String lastProgram) {
        // TODO: implement run command logic.
        // TODO: remove this after implementing command execute logic.
        registersCommand.generateRandomRegisterValues();
        return true;
    }
}
