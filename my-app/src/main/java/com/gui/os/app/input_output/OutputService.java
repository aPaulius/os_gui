package com.gui.os.app.input_output;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class OutputService {
    private TextGraphics textGraphics;
    private Screen screen;

    public OutputService(TextGraphics textGraphics, Screen screen) {
        this.textGraphics = textGraphics;
        this.screen = screen;
    }

    public void print(int column, int row, String text) {
        textGraphics.putString(column, row, text);

        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void success(int column, int row, String text) {
        clearForNewCommand();
        textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
        print(column, row, text);
        textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
    }

    public void error(int column, int row, String text) {
        clearForNewCommand();
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        print(column, row, text);
        textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
    }

    public void info(int column, int row, String text) {
        textGraphics.setForegroundColor(TextColor.ANSI.CYAN);
        print(column, row, text);
        textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT);
    }

    public void clearForNewCommand() {
        screen.clear();
        print(1, 1, "Enter a command: ");
    }

    public void clearForNewDebugCommand() {
        screen.clear();
        print(1, 1, "Enter a command [debug]: ");
    }
}
