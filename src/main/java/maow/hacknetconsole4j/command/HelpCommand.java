package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.registry.CommandRegistry;

public class HelpCommand implements Command {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "View a list of all commands and their descriptions.";
    }

    @Override
    public void run(String[] args) {
        for (Command command : CommandRegistry.getAll()) {
            System.out.println(command.getName() + " - " + command.getDescription());
        }
    }
}
