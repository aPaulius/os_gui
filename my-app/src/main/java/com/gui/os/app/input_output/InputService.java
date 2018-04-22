package com.gui.os.app.input_output;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.gui.os.app.CommandService;
import java.io.IOException;
import java.util.HashMap;

public class InputService {
    private Screen screen;
    private CommandService cs;
    private OutputService os;

    public InputService(Screen screen, CommandService cs, OutputService os) {
        this.screen = screen;
        this.cs = cs;
        this.os = os;
    }

    public void read() throws IOException {
        HashMap<String, String> data = new HashMap<String, String>();
        KeyStroke keyStroke;
        String command = "";
        int position = 0;

        while (true) {
            keyStroke = screen.readInput();

            if (isEnter(keyStroke)) {
                cs.execute(command);
                command = "";
                position = 0;
            } else if (isBackspace(keyStroke)) {
                if (command.length() > 0) {
                    command = command.substring(0, command.length() - 1);
                }

                if (position > 0) {
                    os.print(18 + --position, 1, " ");
                }
            } else if (keyStroke != null) {
                String key = keyStroke.getCharacter() + "";
                command += key;
                os.print(18 + position++, 1, keyStroke.getCharacter() + "");
            }
        }
    }

    protected boolean isEnter(KeyStroke keyStroke) {
        return keyStroke != null && keyStroke.getKeyType() == KeyType.Enter;
    }

    protected boolean isBackspace(KeyStroke keyStroke) {
        return keyStroke != null && keyStroke.getKeyType() == KeyType.Backspace;
    }
}
