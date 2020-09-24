package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;

public class DcCommand implements Command {
    @Override
    public String getName() {
        return "dc";
    }

    @Override
    public String getDescription() {
        return "Disconnect from the currently connected node.";
    }

    @Override
    public void run(String[] args) {
        if (Terminal.getConnectedNode() != null) {
            Terminal.setConnectedNode(null);
        } else {
            System.out.println("You are not connected to a node.");
        }
    }
}
