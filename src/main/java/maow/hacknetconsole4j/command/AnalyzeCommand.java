package maow.hacknetconsole4j.command;

import maow.hacknetconsole4j.Terminal;
import maow.hacknetconsole4j.computer.Node;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnalyzeCommand implements Command {
    private final Random random = new Random();
    private int iterations = 0;
    private final List<String> strings = new ArrayList<>();
    private boolean generated = false;

    @Override
    public String getName() {
        return "analyze";
    }

    @Override
    public String getDescription() {
        return "Analyze a node's firewall.";
    }

    @Override
    public void run(String[] args) {
        Node node = Terminal.getConnectedNode();
        if (node != null) {
            if (node.getFirewall() != null) {
                iterations++;
                String password = node.getFirewall().getPassword();
                if (!generated) {
                    generated = true;
                    generateLines(password);
                }
                int i = 0;
                for (String string : strings) {
                    try { Thread.sleep(node.getFirewall().getSpeed()); } catch (InterruptedException e) { e.printStackTrace(); }
                    if (i < iterations) {
                        System.out.println("000" + password.charAt(i) + "0000");
                    } else {
                        System.out.println(string);
                    }
                    i++;
                }
            }
        }
    }

    private void generateLines(String password) {
        for (char character : password.toCharArray()) {
            strings.add(insertChar(generateLine(), character).replaceAll("\\n", "\\n"));
        }
    }

    private String generateLine() {
        byte[] array = new byte[8];
        random.nextBytes(array);
        return new String(array, StandardCharsets.US_ASCII);
    }

    private String insertChar(String string, char character) {
        int index = random.nextInt(8);
        StringBuilder builder = new StringBuilder(string);
        builder.setCharAt(index, character);
        return builder.toString();
    }
}
