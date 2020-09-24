package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.registry.CommandRegistry;
import maow.hacknetconsole4j.registry.NodeRegistry;

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
        if (args.length > 1) {
            Node node = NodeRegistry.get(args[1]);
            if (node != null) {
                Terminal.setConnectedNode(node);
                ((AnalyzeCommand) CommandRegistry.get("analyze")).setIterations(0);
                System.out.println("Connected to " + node.getIpAddress());
            } else {
                System.out.println("Node " + args[1] + " not found.");
            }
        } else {
            System.out.println("Incorrect syntax.\nSyntax: connect <ip address>");
        }
    }
}
