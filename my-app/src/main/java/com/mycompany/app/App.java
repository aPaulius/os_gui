package com.mycompany.app;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Screen screen = createScreen();
        TextGraphics textGraphics = screen.newTextGraphics();

        addText(textGraphics, screen, 1, 1, "Enter a command: ");

        readInput(textGraphics, screen);
    }

    private static void readInput(TextGraphics textGraphics, Screen screen) throws IOException {
        String command = "";
        int position = 0;

        while (true) {
            KeyStroke keyStroke = screen.readInput();

            if (keyStroke != null && keyStroke.getKeyType() == KeyType.Enter) {
                addText(textGraphics, screen, 1, 3, command);
                break;
            } else if (keyStroke != null) {
                String key = keyStroke.getCharacter() + "";
                command += key;
                addText(textGraphics, screen, 18 + position++, 1, keyStroke.getCharacter() + "");
            }
        }
    }

    private static void addText(TextGraphics textGraphics, Screen screen, int column, int row, String text) throws IOException {
        textGraphics.putString(column, row, text);
        screen.refresh();
    }

    private static Screen createScreen() throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Screen screen = defaultTerminalFactory.createScreen();
        screen.startScreen();

        return screen;
    }
}
