package maow.hacknetconsole4j.computer.program;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.Port;

public class PortHackProgram implements Program {
    @Override public String getName() {
        return "PortHack";
    }
    @Override public int getTimeRunning() { return 5000; }
    @Override public boolean isBuiltIn() {
        return true;
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        if (node != null) {
            if (node.getFirewall() != null && !node.getFirewall().isBypassed()) {
                System.out.println("Cannot execute PortHack. Firewall is active.");
                return;
            }
            int openPorts = 0;
            for (Port port : node.getPorts()) {
                if (port.isOpen()) openPorts++;
            }
            if (openPorts >= node.getPortsToCrack()) {
                System.out.println("Computer " + node.getIpAddress() + " cracked.");
                node.crack();
            } else {
                System.out.println("Crack requires " + node.getPortsToCrack() + " open ports.");
            }
        } else {
            System.out.println("You are not connected to a node.");
        }
    }
}
