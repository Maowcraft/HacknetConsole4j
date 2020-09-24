package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;

public class SolveCommand implements Command {
    @Override
    public String getName() {
        return "solve";
    }

    @Override
    public String getDescription() {
        return "Solve and bypass a whitelist using specified password.";
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        if (node != null) {
            if (args.length > 1) {
                if (node.getFirewall() != null) {
                    if (args[1].equals(node.getFirewall().getPassword())) {
                        node.getFirewall().setBypassed(true);
                        System.out.println("Correct password found. Firewall bypassed.");
                    } else {
                        System.out.println("Incorrect firewall password.");
                    }
                } else {
                    System.out.println("This node does not have a firewall.");
                }
            } else {
                System.out.println("Incorrect syntax.\nSyntax: solve <password>");
            }
        } else {
            System.out.println("You are not connected to a node.");
        }
    }
}
