package com.gui.os.app.command;

import com.gui.os.app.input_output.OutputService;
import java.util.*;

public class RegistersCommand {
    private OutputService os;
    private HashMap<String, String> registers;

    public RegistersCommand(OutputService os) {
        this.os = os;
        this.registers = new HashMap<String, String>();
        initialize();
    }

    public void printRegisters() {
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

        int column = 1;
        Iterator it = registers.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            os.info(column, 3, pair.getKey() + ": " + pair.getValue());
            column += 9;
        }
    }

    // TODO: remove this after implementing command execute logic.
    public void generateRandomRegisterValues() {
        Iterator it = registers.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            pair.setValue(getRandomRegisterValue());
        }
    }

    // TODO: remove this after implementing command execute logic.
    public String getRandomRegisterValue() {
        String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder value = new StringBuilder();
        Random rnd = new Random();
        while (value.length() < 4) {
            int index = (int) (rnd.nextFloat() * data.length());
            value.append(data.charAt(index));
        }

        return value.toString();

    }

    private void initialize() {
        registers.put("A", null);
        registers.put("B", null);
        registers.put("C", null);
        registers.put("D", null);
        registers.put("ST", null);
        registers.put("CI", null);
        registers.put("PI", null);
        registers.put("SI", null);
        registers.put("TI", null);
        registers.put("CS", null);
        registers.put("PTR", null);
    }
}
