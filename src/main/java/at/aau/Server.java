package at.aau;

import at.aau.commandHandler.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;

public class Server {
    private final Game game;
    private final CommandHandler commandHandler;
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    public Server(Game game, CommandHandler commandHandler) {
        this.game = game;
        this.commandHandler = commandHandler;
    }

    void start() {
        logger.info("Server started");
        try (var serverSocket = new ServerSocket(8080)) {
            while (true) {
                new Connection(serverSocket.accept(), game, commandHandler).start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
