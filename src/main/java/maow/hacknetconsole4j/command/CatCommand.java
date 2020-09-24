package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.account.Account;
import maow.hacknetconsole4j.computer.account.AccountType;
import maow.hacknetconsole4j.computer.filesystem.File;
import maow.hacknetconsole4j.computer.filesystem.Folder;

public class CatCommand implements Command {
    @Override
    public String getName() {
        return "cat";
    }

    @Override
    public String getDescription() {
        return "Print the contents of a file.";
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        if (node != null) {
            if (args.length > 1) {
                boolean fileFound = false;
                Account activeAccount = node.getActiveAccount();
                Folder activeFolder = node.getActiveFolder();
                if (activeAccount.getType() == AccountType.ADMIN || activeAccount.getType() == AccountType.ALL) {
                    for (File file : activeFolder.getFiles()) {
                        if (file.getFileName().equals(args[1])) {
                            System.out.println(file.getFileContents());
                            fileFound = true;
                        }
                    }
                    if (!fileFound) {
                        System.out.println("File " + args[1] + " not found.");
                    }
                } else {
                    System.out.println("You have insufficient permissions to perform this action.");
                }
            } else {
                System.out.println("Incorrect syntax.\nSyntax: cat <file>");
            }
        } else {
            System.out.println("You are not connected to a node.");
        }
    }
}
