package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.registry.NodeRegistry;

import java.util.Objects;

public class ConnectCommand implements Command {
    @Override
    public String getName() {
        return "connect";
    }

    @Override
    public String getDescription() {
        return "Connect to a node.";
    }

    @Override
    public void run(String[] args) {
        Terminal.setConnectedNode(Objects.requireNonNull(NodeRegistry.get(args[1])));
    }
}
