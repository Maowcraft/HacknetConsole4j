package maow.hacknetconsole4j.computer.program;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;

import java.util.HashMap;

public class SSLTrojanProgram implements Program {
    private final HashMap<String, Integer> types = new HashMap<>();

    {
        types.put("-s", 22);
        types.put("-f", 21);
        types.put("-w", 80);
        types.put("-r", 554);
    }

    @Override
    public String getName() {
        return "SSLTrojan";
    }

    @Override
    public int getTimeRunning() {
        return 7000;
    }

    @Override
    public boolean isBuiltIn() {
        return false;
    }

    @Override
    public void run(String[] args) {
        if (args.length > 3) {
            Node node = Terminal.getConnectedNode();
            if (node != null) {
                int port = Integer.parseInt(args[1]);
                String type = args[2];
                int secondPort = Integer.parseInt(args[3]);
                if (port == 443) {
                   if (types.get(type) == secondPort && node.isPortOpen(secondPort)) {
                       node.setPortOpen(port);
                       System.out.println("SSLTrojan has finished, port 443 is now open.");
                   }
                } else {
                    incorrectUsage();
                }
            }
        } else {
            incorrectUsage();
        }
    }

    private void incorrectUsage() {
        System.out.println("Incorrect command usage.\nUsage: SSLTrojan <port> -[s/f/w/r] <second port>");
    }
}
