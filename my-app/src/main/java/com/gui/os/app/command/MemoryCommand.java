package com.gui.os.app.command;

import com.gui.os.app.input_output.OutputService;
import java.util.Random;

public class MemoryCommand {
    private OutputService os;
    private String[][] memory;

    public MemoryCommand(OutputService os) {
        this.os = os;
        this.memory = new String[16][16];
        initialize();
    }

    public void printMemory() {
        execute(false);
    }

    /**
     * @param params - pass boolean param if you want to turn off out screen clearing.
     */
    public void execute(Boolean... params) {
        assert params.length <= 1;
        boolean clearScreen = params.length == 0 ? true : false;

        if (clearScreen) {
            os.clearForNewCommand();
        }

        int column = 4;
        int row = 8;

        printHeader(column);
        printRowNumbers(row);

        for (int i = 0; i < 16; i++) {
            printBlock(column, row++, i);
        }

    }

    public void execute(String data) {
        os.clearForNewCommand();

        int blockNumber = 0;

        try {
            blockNumber = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            os.error(1, 3, "Block not found. Type 'memory [1-16]'");
            return;
        }

        if (blockNumber < 1 || blockNumber > 16) {
            os.error(1, 3, "Block not found. Type 'memory [1-16]'");
            return;
        }

        int column = 4;
        int row = 8;

        printHeader(column);
        printRowNumber(row, blockNumber);
        printBlock(column, row, blockNumber);
    }

    public String[][] getMemory() {
        return memory;
    }

    // TODO: remove after implement load command logic.
    public void generateRandomMemory() {
        memory[0][0] = getRandomCommand();
        memory[0][1] = getRandomCommand();
        memory[0][2] = getRandomCommand();
        memory[0][3] = getRandomCommand();
        memory[0][4] = getRandomCommand();
    }

    // TODO: remove after implement load command logic.
    private String getRandomCommand() {
        String[] commands = {"LR10", "SR20", "XCPR", "ADD", "SUB", "COMP", "MUL"};
        int random = new Random().nextInt(commands.length);

        return commands[random];
    }

    private void printHeader(int column) {
        for (int i = 1; i <= 16; i++) {
            os.info(column, 7, String.format("| %02d |", i));
            column += 5;
        }
    }

    private void printRowNumbers(int row) {
        for (int i = 1; i <= 16; i++) {
            printRowNumber(row++, i);
        }
    }

    private void printRowNumber(int row, int rowNumber) {
        os.info(1, row, String.format("%02d ", rowNumber));
    }

    private void printBlock(int column, int row, int block) {
        for (int j = 0; j < 16; j++) {
            String blockWord = memory[block][j] != null ? memory[block][j] : "";
            os.info(column, row, String.format("|%4s|", blockWord));
            column += 5;
        }
    }

    private void initialize() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                memory[i][j] = null;
            }
        }

        memory[0][0] = "LR10";
        memory[0][1] = "SR20";
        memory[0][2] = "XCPR";
    }
}
