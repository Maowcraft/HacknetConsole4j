package maow.hacknetconsole4j.computer.filesystem;

import maow.hacknetconsole4j.program.Program;

public class File {
    private final String fileName;
    private final String fileContents;
    private final boolean isEncrypted;
    private Program associatedProgram;

    public File(String fileName, String fileContents, boolean isEncrypted, Program associatedProgram) {
        this.fileName = fileName;
        this.fileContents = fileContents;
        this.isEncrypted = isEncrypted;
        this.associatedProgram = associatedProgram;
    }

    public File(String fileName, String fileContents, Program associatedProgram) {
        this(fileName, fileContents, false, associatedProgram);
    }

    public File(String fileName, String fileContents) {
        this(fileName, fileContents, false, null);
    }

    public String getFileName() {
        return fileName;
    }
    public String getFileContents() {
        return fileContents;
    }
    public boolean isEncrypted() {
        return isEncrypted;
    }
    public Program getAssociatedProgram() {
        return associatedProgram;
    }
    public void setAssociatedProgram(Program associatedProgram) {
        this.associatedProgram = associatedProgram;
    }
}
