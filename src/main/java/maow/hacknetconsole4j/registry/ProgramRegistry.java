package maow.hacknetconsole4j.registry;

import maow.hacknetconsole4j.computer.program.Program;

import java.util.HashMap;

public class ProgramRegistry {
    private static final HashMap<String, Program> programs = new HashMap<>();

    public static void register(Program program) {
        programs.put(program.getName(), program);
    }

    public static Program get(String name) {
        return programs.get(name);
    }

    public static HashMap<String, Program> getAll() {
        return programs;
    }
}
