package maow.hacknetconsole4j.registry;

import maow.hacknetconsole4j.command.Command;
import maow.hacknetconsole4j.computer.program.Program;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistry {
    private static final List<Command> commands = new ArrayList<>();

    public static void register(Command command) {
        commands.add(command);
    }

    public static Command get(String name) {
        for (Command command : commands) {
            if (command.getName().equals(name)) {
                return command;
            }
        }
        return null;
    }

    public static List<Command> getAll() {
        return commands;
    }
}
