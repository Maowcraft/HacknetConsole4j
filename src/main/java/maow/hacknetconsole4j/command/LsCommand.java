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
            System.out.println("Files in " + activeFolder.getName() + ":\n");
            for (File file : activeFolder.getFiles()) {
                if (file != null) {
                    System.out.println(file.getFileName());
                }
            }
            for (Folder folder : activeFolder.getNestedFolders()) {
                if (folder != null) {
                    System.out.println("<DIR> " + folder.getName());
                }
            }
        } else {
            System.out.println("You are not connected to a node.");
        }
    }
}
