package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.account.Account;
import maow.hacknetconsole4j.computer.account.AccountType;

public class ScanCommand implements Command {
    @Override
    public String getName() {
        return "scan";
    }

    @Override
    public String getDescription() {
        return "View all linked nodes on connected node.";
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        if (node != null) {
            Account account = node.getActiveAccount();
            if (account.getType() == AccountType.ADMIN || account.getType() == AccountType.ALL) {
                if (node.getLinkedNodes().length <= 0) {
                    System.out.println("None found.");
                } else {
                    System.out.println("Scanning.");
                    for (Node linkedNode : node.getLinkedNodes()) {
                        System.out.println("Found: " + linkedNode.getName() + " (" + linkedNode.getIpAddress() + ")");
                    }
                }
            } else {
                System.out.println("You have insufficient permissions to perform this action.");
            }
        } else {
            System.out.println("You are not connected to a node.");
        }
    }
}
