package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.filesystem.File;
import maow.hacknetconsole4j.computer.filesystem.Folder;

public class LsCommand implements Command {
    @Override
    public String getName() {
        return "ls";
    }

    @Override
    public String getDescription() {
        return "View a list of all files in the active folder.";
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        if (node != null) {
            Folder activeFolder = node.getActiveFolder();
            if (activeFolder == null) {
                if (node.getFilesystem() != null && node.getFilesystem().getFolders() != null) {
                    node.setActiveFolder(node.getFilesystem().getFolders()[0]);
                } else {
                    System.out.println("This filesystem has no folders.");
                    return;
                }
            }
            System.out.println("Files in " + node.getActiveFolder().getName() + ":\n");
            if (node.getActiveFolder().getFiles() != null) {
                for (File file : node.getActiveFolder().getFiles()) {
                    if (file != null) {
                        System.out.println(file.getFileName());
                    }
                }
            }
            if (node.getActiveFolder().getNestedFolders() != null) {
                for (Folder folder : node.getActiveFolder().getNestedFolders()) {
                    if (folder != null) {
                        System.out.println("<DIR> " + folder.getName());
                    }
                }
            }
        }
    }
}
