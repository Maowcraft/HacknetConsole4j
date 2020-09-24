package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.security.Proxy;

public class BypassCommand implements Command {
    @Override
    public String getName() {
        return "bypass";
    }

    @Override
    public String getDescription() {
        return "Start a proxy bypass, requires a 1 or more shells.";
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        if (node != null) {
            Proxy proxy = node.getProxy();
            if (proxy != null && Terminal.getShells().size() > 0 && !proxy.isBypassed()) {
                System.out.println("Bypassing proxy.");
                int speed = proxy.getSpeed() / Terminal.getShells().size();
                try { Thread.sleep(speed); } catch (InterruptedException e) { e.printStackTrace(); }
                proxy.setBypassed(true);
                System.out.println("Proxy bypassed with " + Terminal.getShells().size() + " shell(s).");
            }
        }
    }
}
