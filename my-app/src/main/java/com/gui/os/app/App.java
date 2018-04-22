package com.gui.os.app;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.gui.os.app.command.*;
import com.gui.os.app.input_output.DebugInputService;
import com.gui.os.app.input_output.InputService;
import com.gui.os.app.input_output.OutputService;
import java.io.IOException;

public class App {
    static ScreenService ss = new ScreenService();
    static Screen screen = ss.create();
    static TextGraphics textGraphics = screen.newTextGraphics();
    static OutputService os = new OutputService(textGraphics, screen);
    static HelpCommand hc = new HelpCommand(os);
    static MemoryCommand mc = new MemoryCommand(os);
    static LoadCommand ls = new LoadCommand(os, mc);
    static RegistersCommand regc = new RegistersCommand(os);
    static RunCommand rc = new RunCommand(ls, os, regc);
    static DebugHelpCommand dhc;
    static DebugCommandService dcs = new DebugCommandService(os);
    static DebugInputService dis = new DebugInputService(screen, dcs, os);
    static DebugCommand dc = new DebugCommand(os, dis, mc, regc);
    static CommandService cs = new CommandService(hc, ls, rc, mc, regc, dc, os);
    static InputService is = new InputService(screen, cs, os);

    public static void main(String[] args) throws IOException {
        os.clearForNewCommand();
        os.print(1, 3, "Type 'help' to find out about available commands.");

        is.read();
    }
}
