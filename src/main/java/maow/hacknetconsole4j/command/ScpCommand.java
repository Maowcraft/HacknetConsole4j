package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.account.Account;
import maow.hacknetconsole4j.computer.account.AccountType;
import maow.hacknetconsole4j.computer.filesystem.File;
import maow.hacknetconsole4j.computer.filesystem.Folder;

import java.util.Objects;

public class ScpCommand implements Command {
    @Override
    public String getName() {
        return "scp";
    }

    @Override
    public String getDescription() {
        return "Download a program to your local files from an external node.";
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        if (node != null && args.length > 1) {
            if (node.getFilesystem() != null && node.getFilesystem().getFolders() != null) {
                Account activeAccount = node.getActiveAccount();
                Folder activeFolder = node.getActiveFolder();
                if (activeFolder == null) {
                    node.setActiveFolder(node.getFilesystem().getFolders()[0]);
                }
                if (activeAccount != null && (activeAccount.getType() == AccountType.ADMIN || activeAccount.getType() == AccountType.ALL)) {
                    if (Objects.requireNonNull(activeFolder).getFiles() != null) {
                        for (File file : activeFolder.getFiles()) {
                            if (file.getFileName().equals(args[1])) {
                                if (file.getAssociatedProgram() != null) {
                                    Terminal.addLocalFile(file);
                                    System.out.println("Downloaded " + file.getFileName() + " from " + node.getIpAddress() + ".");
                                }
                                return;
                            }
                        }
                    }
                } else {
                    System.out.println("You have insufficient permissions to perform this action.");
                }
            }
        }
    }
}
