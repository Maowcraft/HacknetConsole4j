package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.filesystem.Folder;

public class CdCommand implements Command {
    @Override
    public String getName() {
        return "cd";
    }

    @Override
    public String getDescription() {
        return "Change the active directory on a filesystem.";
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        if (node != null) {
            if (args.length > 1) {
                boolean foundFolder = false;
                Folder activeFolder = node.getActiveFolder();
                for (Folder folder : activeFolder.getNestedFolders()) {
                    if (folder.getName().equals(args[1])) {
                        System.out.println("Moved to " + folder.getName() + ".");
                        node.setActiveFolder(folder);
                        foundFolder = true;
                    }
                }
                if (!foundFolder) {
                    System.out.println("Folder " + args[1] + " not found.");
                }
            } else {
                System.out.println("Incorrect syntax.\nSyntax: cd <folder>");
            }
        } else {
            System.out.println("You are not connected to a node.");
        }
    }
}
