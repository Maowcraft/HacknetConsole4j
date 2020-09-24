package maow.hacknetconsole4j;

import maow.hacknetconsole4j.util.ConsoleUtil;

public class HacknetConsole4j {
    public static boolean developerMode = false;

    public static void main(String[] args) {
        ConsoleUtil.clear();
        if (args.length > 0) {
            developerMode = Boolean.parseBoolean(args[0]);
        }
        BaseContent.registerAll();
        System.out.print("Welcome to the HacknetConsole4j beta. The only computers atm are:\n\n127.0.0.1\n111.111.111.111\n\nHowever, you can modify this project to add your own commands, programs, and nodes!\nType 'help' to view a list of all commands.\n\n\n");
        Terminal.checkInput();
    }
}
