package maow.hacknetconsole4j;

import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.registry.NodeRegistry;
import maow.hacknetconsole4j.util.ConsoleUtil;

public class HacknetConsole4j {
    public static boolean developerMode = false;

    public static void main(String[] args) {
        ConsoleUtil.clear();
        if (args.length > 0) {
            developerMode = Boolean.parseBoolean(args[0]);
        }
        BaseContent.registerAll();
        System.out.println("Welcome to the HacknetConsole4j beta. Current computer list:\n");
        for (Node node : NodeRegistry.getAll().values()) {
            System.out.println(node.getName() + " - " + node.getIpAddress());
        }
        System.out.println("\nYou can modify this project to add your own commands, programs, and nodes!\nType 'help' to view a list of all commands.\n\n\n");
        Terminal.checkInput();
    }
}
