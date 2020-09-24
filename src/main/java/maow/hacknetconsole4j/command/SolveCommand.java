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
            if (args.length > 1 && node.getFirewall() != null) {
                if (args[1].equals(node.getFirewall().getPassword())) {
                    node.getFirewall().setBypassed(true);
                    System.out.println("Correct password found. Firewall bypassed.");
                }
            }
        }
    }
}
