package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;

public class FhCommand implements Command {
    @Override
    public String getName() {
        return "fh";
    }

    @Override
    public String getDescription() {
        return "DEV COMMAND : Instantly crack a node.";
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        if (node != null) {
            node.crack();
            System.out.println("Dirty cheater...");
        }
    }
}
