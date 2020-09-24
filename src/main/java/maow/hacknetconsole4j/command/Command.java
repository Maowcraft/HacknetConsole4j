package maow.hacknetconsole4j.command;

public interface Command {
    String getName();
    String getDescription();
    void run(String[] args);
}
