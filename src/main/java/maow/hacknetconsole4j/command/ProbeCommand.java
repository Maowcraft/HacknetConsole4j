package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.Port;

public class ProbeCommand implements Command {
    @Override
    public String getName() {
        return "probe";
    }

    @Override
    public String getDescription() {
        return "Probe a node using nmap to find port information.";
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        if (node != null) {
            System.out.println("-- " + node.getName() + " --");
            if (node.getFirewall() != null) {
                System.out.println("[Firewall Active]");
            }
            if (node.getProxy() != null) {
                System.out.println("[Proxy Active]");
            }
            if (node.getPorts().length > 0) {
                System.out.println("Ports:\n");
                for (Port port : node.getPorts()) {
                    System.out.println(port.getPortNumber() + " - " + port.getPortName() + " - [" + (port.isOpen() ? "OPEN" : "CLOSED") + "]");
                }
            }

            System.out.println("\nOpen Ports to Crack: " + node.getPortsToCrack());
        } else {
            System.out.println("You are not connected to a node.");
        }
    }
}
