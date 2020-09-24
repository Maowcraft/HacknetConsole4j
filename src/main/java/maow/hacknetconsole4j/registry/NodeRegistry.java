package maow.hacknetconsole4j.registry;

import maow.hacknetconsole4j.computer.Node;

import java.util.HashMap;

public class NodeRegistry {
    private static final HashMap<String, Node> nodes = new HashMap<>();

    public static void register(Node node) {
        nodes.put(node.getIpAddress(), node);
    }

    public static Node get(String ip) {
        return nodes.get(ip);
    }

    public static HashMap<String, Node> getAll() {
        return nodes;
    }
}
