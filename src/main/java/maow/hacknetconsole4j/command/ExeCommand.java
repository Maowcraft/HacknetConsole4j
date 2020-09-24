package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.filesystem.File;
import maow.hacknetconsole4j.program.Program;
import maow.hacknetconsole4j.registry.ProgramRegistry;

public class ExeCommand implements Command {
    @Override
    public String getName() {
        return "exe";
    }

    @Override
    public String getDescription() {
        return "View a list of all of your executables.";
    }

    @Override
    public void run(String[] args) {
        for (Program program : ProgramRegistry.getAll().values()) {
            if (program.isBuiltIn()) {
                System.out.println(program.getName() + ".exe");
            }
        }
        for (File file : Terminal.getLocalFiles()) {
            if (file.getAssociatedProgram() != null) {
                System.out.println(file.getFileName());
            }
        }
    }
}
