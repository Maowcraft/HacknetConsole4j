package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.account.Account;

public class LoginCommand implements Command {
    @Override
    public String getName() {
        return "login";
    }

    @Override
    public String getDescription() {
        return "Login to an account on a node.";
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        if (node != null && args.length > 2) {
            String username = args[1];
            String password = args[2];
            if (node.getAccounts() != null) {
                for (Account account : node.getAccounts()) {
                    if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                        node.setActiveAccount(account);
                        System.out.println("Logged in.\nWelcome, " + account.getUsername() + ".");
                    } else {
                        System.out.println("Incorrect username or password.");
                    }
                    return;
                }
            } else {
                System.out.println("This node has no accounts.");
            }
        }
    }
}
