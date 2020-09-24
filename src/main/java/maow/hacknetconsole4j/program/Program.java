package maow.hacknetconsole4j.program;

public interface Program {
    String getName();
    int getTimeRunning();
    boolean isBuiltIn();
    void run(String[] args);
}
