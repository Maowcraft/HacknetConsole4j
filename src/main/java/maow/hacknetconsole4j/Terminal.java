package maow.hacknetconsole4j;

import maow.hacknetconsole4j.command.Command;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.filesystem.File;
import maow.hacknetconsole4j.computer.program.Program;
import maow.hacknetconsole4j.registry.CommandRegistry;
import maow.hacknetconsole4j.registry.ProgramRegistry;
import maow.hacknetconsole4j.util.ConsoleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Terminal {
    public static final Scanner scanner = new Scanner(System.in);
    private static Node connectedNode;
    private static final List<File> localFiles = new ArrayList<>();
    private static final List<Node> shells = new ArrayList<>();

    public static void runCommand(String fullCommand) {
        String[] args = fullCommand.split("\\s+");
        String commandString = args[0];
        Program program = ProgramRegistry.get(commandString);
        Command command = CommandRegistry.get(commandString);
        if (command != null) {
            command.run(args);
        } else if (program != null) {
            boolean ownedProgram = false;
            for (File file : localFiles) {
                Program associatedProgram = file.getAssociatedProgram();
                if ((associatedProgram != null && associatedProgram.getName().equals(commandString))) {
                    ownedProgram = true;
                }
            }
            if (ownedProgram || program.isBuiltIn()) {
                System.out.println("Attempting to run " + program.getName() + " now.");
                try { Thread.sleep(program.getTimeRunning()); } catch (InterruptedException e) { e.printStackTrace(); }
                program.run(args);
            } else {
                System.out.println("Command not found: " + fullCommand);
            }
        } else {
            System.out.println("Command not found: " + fullCommand);
        }
        checkInput();
    }

    public static void checkInput() {
        System.out.print("#> ");
        String command = scanner.nextLine();
        runCommand(command);
    }

    public static Node getConnectedNode() {
        return connectedNode;
    }
    public static void setConnectedNode(Node node) {
        ConsoleUtil.print("Connected to " + node.getIpAddress());
        connectedNode = node;
    }
    public static List<File> getLocalFiles() {
        return localFiles;
    }
    public static void addLocalFile(File file) { localFiles.add(file); }
    public static List<Node> getShells() {
        return shells;
    }
    public static void addShell(Node node) {
        shells.add(node);
    }
    public static void removeShell(Node node) {
        shells.remove(node);
    }
}
