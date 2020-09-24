package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.filesystem.File;
import maow.hacknetconsole4j.program.Program;
import maow.hacknetconsole4j.registry.ProgramRegistry;

public class GiveCommand implements Command {
    @Override
    public String getName() {
        return "give";
    }

    @Override
    public String getDescription() {
        return "DEV COMMAND : Adds a program to the terminal's local files.";
    }

    @Override
    public void run(String[] args) {
        if (args.length > 1) {
            Program program = ProgramRegistry.get(args[1]);
            if (program != null) {
                Terminal.addLocalFile(new File(program.getName() + ".exe", "10101", program));
                System.out.println("Gave the terminal a cookie, and a program. :)");
            } else {
                System.out.println("This program does not exist: " + args[1]);
            }
        } else {
            System.out.println("Program not specified.");
        }
    }
}
