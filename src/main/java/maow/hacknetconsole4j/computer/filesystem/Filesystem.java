package maow.hacknetconsole4j.computer.filesystem;

public class Filesystem {
    private final Folder[] folders;

    public Filesystem(Folder[] folders) {
        this.folders = folders;
    }

    public Folder[] getFolders() {
        if (folders == null) {
            return new Folder[]{
                    new Folder("/", new File[]{})
            };
        }
        return folders;
    }
}
