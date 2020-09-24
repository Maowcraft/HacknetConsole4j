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

    public Folder(String name, File[] files) {
        this(name, files, new Folder[]{});
    }

    public String getName() {
        return name;
    }
    public File[] getFiles() {
        if (files == null) {
            return new File[]{};
        }
        return files;
    }
    public Folder[] getNestedFolders() {
        if (nestedFolders == null) {
            return new Folder[]{};
        }
        return nestedFolders;
    }
}
