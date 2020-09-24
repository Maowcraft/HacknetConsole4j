package maow.hacknetconsole4j.computer.program;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;

public class CrackProgram implements Program {
    private final String name;
    private final int portToCrack;
    private final int timeRunning;

    public CrackProgram(String name, int portToCrack, int timeRunning) {
        this.name = name;
        this.portToCrack = portToCrack;
        this.timeRunning = timeRunning;
    }

    @Override public boolean isBuiltIn() {
        return false;
    }
    @Override public String getName() {
        return name;
    }
    @Override public int getTimeRunning() {
        return timeRunning;
    }
    public int getPortToCrack() {
        return portToCrack;
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        try {
            if (node != null) {
                if (node.getProxy() != null && !node.getProxy().isBypassed()) {
                    System.out.println("Cannot execute program. Proxy is active.");
                    return;
                }
                if (args.length > 1) {
                    if (Integer.parseInt(args[1]) == portToCrack) {
                        node.setPortOpen(portToCrack);
                        System.out.println(name + " has finished, port " + portToCrack + " is now open.");
                    } else {
                        System.out.println("Port " + args[0] + " cannot be cracked using " + name);
                    }
                }
            } else {
                System.out.println("You are not connected to a node.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
