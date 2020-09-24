package maow.hacknetconsole4j.registry;

import maow.hacknetconsole4j.computer.Node;

import java.util.ArrayList;
import java.util.List;

public class NodeRegistry {
    private static final List<Node> nodes = new ArrayList<>();

    public static void register(Node node) {
        nodes.add(node);
    }

    public static Node get(String ip) {
        for (Node node : nodes) {
            if (ip.equals(node.getIpAddress())) {
                return node;
            }
        }
        return null;
    }

    public static List<Node> getAll() {
        return nodes;
    }
}
