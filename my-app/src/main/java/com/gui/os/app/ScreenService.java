package com.gui.os.app;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;

public class ScreenService {
    public Screen create() {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Screen screen = null;

        try {
            defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(120, 30));
            screen = defaultTerminalFactory.createScreen();
            screen.startScreen();
            screen.setCursorPosition(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screen;
    }
}
