package maow.hacknetconsole4j.registry;

import maow.hacknetconsole4j.computer.program.Program;

import java.util.ArrayList;
import java.util.List;

public class ProgramRegistry {
    private static final List<Program> programs = new ArrayList<>();

    public static void register(Program program) {
        programs.add(program);
    }

    public static Program get(String name) {
        for (Program program : programs) {
            if (program.getName().equals(name)) {
                return program;
            }
        }
        return null;
    }

    public static List<Program> getAll() {
        return programs;
    }
}
