package com.gui.os.app.input_output;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.gui.os.app.DebugCommandService;
import java.io.IOException;
import java.util.HashMap;

public class DebugInputService {
    private Screen screen;
    private DebugCommandService dcs;
    private OutputService os;

    public DebugInputService(Screen screen, DebugCommandService dcs, OutputService os) {
        this.screen = screen;
        this.dcs = dcs;
        this.os = os;
    }

    public boolean read() throws IOException {
        HashMap<String, String> data = new HashMap<String, String>();
        KeyStroke keyStroke;
        String command = "";
        int position = 0;

        while (true) {
            keyStroke = screen.readInput();

            if (isEnter(keyStroke)) {
                return dcs.execute(command);
            } else if (isBackspace(keyStroke)) {
                if (command.length() > 0) {
                    command = command.substring(0, command.length() - 1);
                }

                if (position > 0) {
                    os.print(26 + --position, 1, " ");
                }
            } else if (keyStroke != null) {
                String key = keyStroke.getCharacter() + "";
                command += key;
                os.print(26 + position++, 1, keyStroke.getCharacter() + "");
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
