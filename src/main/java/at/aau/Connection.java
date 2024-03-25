package at.aau;

import at.aau.commandHandler.CommandHandler;
import at.aau.commandHandler.CommandType;
import at.aau.models.NoOpPayload;
import at.aau.models.Player;
import at.aau.models.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Connection extends Thread {
    private final Socket socket;
    private static Set<Connection> connections = Collections.synchronizedSet(new HashSet<>());

    public Connection(Socket socket) {
        connections.add(this);
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                var in = new DataInputStream(socket.getInputStream());
                var out = new DataOutputStream(socket.getOutputStream());
        ) {
            while (true) {
//                TODO: deserialize requests
                CommandHandler.execute(new Request(CommandType.PING, new NoOpPayload()), new Player(socket, "name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
