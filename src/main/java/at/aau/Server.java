package at.aau;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;

public class Server {
    private final Game game;
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    public Server(Game game) {
        this.game = game;
    }

    void start() {
        logger.info("Server started");
        try (var serverSocket = new ServerSocket(8080)) {
            while (true) {
                new Connection(serverSocket.accept(), game).start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
