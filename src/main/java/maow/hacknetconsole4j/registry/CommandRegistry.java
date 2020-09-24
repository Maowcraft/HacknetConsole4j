package maow.hacknetconsole4j.registry;

import maow.hacknetconsole4j.command.Command;

import java.util.HashMap;

public class CommandRegistry {
    private static final HashMap<String, Command> commands = new HashMap<>();

    public static void register(Command command) {
        commands.put(command.getName(), command);
    }

    public static Command get(String name) {
        return commands.get(name);
    }

    public static HashMap<String, Command> getAll() {
        return commands;
    }
}
