package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.filesystem.Folder;

import java.util.Objects;

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
        if (node != null && args.length > 1) {
            if (node.getFilesystem() != null && node.getFilesystem().getFolders() != null) {
                Folder activeFolder = node.getActiveFolder();
                if (activeFolder == null) {
                    node.setActiveFolder(node.getFilesystem().getFolders()[0]);
                }
                if (Objects.requireNonNull(activeFolder).getNestedFolders() != null) {
                    for (Folder folder : activeFolder.getNestedFolders()) {
                        if (folder.getName().equals(args[1])) {
                            System.out.println("Moved to " + folder.getName() + ".");
                            node.setActiveFolder(folder);
                        }
                        return;
                    }
                }
            } else {
                System.out.println("This filesystem contains no folders.");
            }
        }
    }
}
