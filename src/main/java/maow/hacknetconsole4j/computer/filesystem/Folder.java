package maow.hacknetconsole4j.computer.filesystem;

public class Folder {
    private final String name;
    private final File[] files;
    private final Folder[] nestedFolders;

    public Folder(String name, File[] files, Folder[] nestedFolders) {
        this.name = name;
        this.files = files;
        this.nestedFolders = nestedFolders;
    }

    public String getName() {
        return name;
    }
    public File[] getFiles() {
        return files;
    }
    public Folder[] getNestedFolders() {
        return nestedFolders;
    }
}
