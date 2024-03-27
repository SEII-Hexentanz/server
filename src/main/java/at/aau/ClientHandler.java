package at.aau;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClientHandler {
    private static final Set<Connection> connections = Collections.synchronizedSet(new HashSet<>());

    public static void addConnection(Connection connection) {
        connections.add(connection);
    }

    public static void removeConnection(Connection connection) {
        connections.remove(connection);
    }

    // Broadcasting
    public static void broadcastMessage(String message) {
        for (Connection connection : connections) {
            connection.sendMessage(message);
        }
    }

    //implement more methods if needed

}
