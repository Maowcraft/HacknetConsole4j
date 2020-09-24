package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.account.Account;
import maow.hacknetconsole4j.computer.account.AccountType;

public class ShellCommand implements Command {
    @Override
    public String getName() {
        return "shell";
    }

    @Override
    public String getDescription() {
        return "Add active node to your shell list, requires admin.";
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        if (node != null) {
            Account activeAccount = node.getActiveAccount();
            if (activeAccount != null && (activeAccount.getType() == AccountType.ADMIN || activeAccount.getType() == AccountType.ALL)) {
                if (!Terminal.getShells().contains(node)) {
                    Terminal.addShell(node);
                    System.out.println("Applied a shell to " + node.getIpAddress() + " successfully.");
                } else {
                    Terminal.removeShell(node);
                    System.out.println("Removed shell from " + node.getIpAddress() + " successfully.");
                }
            } else {
                System.out.println("You have insufficient permissions to perform this action.");
            }
        }
    }
}
