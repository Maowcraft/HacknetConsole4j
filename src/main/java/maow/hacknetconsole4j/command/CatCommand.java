package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.account.Account;
import maow.hacknetconsole4j.computer.account.AccountType;
import maow.hacknetconsole4j.computer.filesystem.File;
import maow.hacknetconsole4j.computer.filesystem.Folder;

import java.util.Objects;

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
        if (node != null && args.length > 1) {
            Account activeAccount = node.getActiveAccount();
            Folder activeFolder = node.getActiveFolder();
            if (activeFolder == null) {
                node.setActiveFolder(node.getFilesystem().getFolders()[0]);
            }
            if (activeAccount != null && (activeAccount.getType() == AccountType.ADMIN || activeAccount.getType() == AccountType.ALL)) {
                for (File file : Objects.requireNonNull(activeFolder).getFiles()) {
                    if (file.getFileName().equals(args[1])) {
                        System.out.println(file.getFileContents());
                    }
                    return;
                }
            } else {
                System.out.println("You have insufficient permissions to perform this action.");
            }
        }
    }
}
