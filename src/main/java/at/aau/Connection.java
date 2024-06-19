package at.aau;

import at.aau.commandHandler.CommandHandler;
import at.aau.models.Request;
import at.aau.models.Response;
import at.aau.payloads.RegisterPayload;
import at.aau.util.SecureObjectInputStream;
import at.aau.values.CommandType;
import at.aau.values.ResponseType;
import io.vavr.control.Option;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Objects;

public class Connection extends Thread {
    private final Socket socket;
    private ObjectOutputStream out;
    private final Game game;
    private final CommandHandler commandHandler;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(Connection.class);

    public Connection(Socket socket, Game game, CommandHandler commandHandler) {
        this.socket = socket;
        this.game = game;
        this.commandHandler = commandHandler;
    }

    @Override
    public void run() {
        logger.info("Connection established");
        try (
                var in = new SecureObjectInputStream(socket.getInputStream());
        ) {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                Request request;
                try {
                    request = (Request) in.readObject();
                } catch (ClassNotFoundException e) {
                    logger.error("Failed to read object", e);
                    send(ResponseType.BAD_REQUEST);
                    continue;
                }
                logger.info("Received request: {}", request);
                if ((request.commandType() == CommandType.REGISTER || request.commandType() == CommandType.RECONNECT) && request.payload() instanceof RegisterPayload payload) {
                    var player = new Player(this, payload.name(), payload.age());
                    commandHandler.execute(request, player, game);
                    continue;
                }
                game.getPlayers().stream().filter(player -> Objects.equals(player.connection().getOrNull(), this))
                        .findFirst().ifPresentOrElse(player -> commandHandler.execute(request, player, game),
                                () -> send(new Response(ResponseType.NOT_REGISTERED)));
            }
        } catch (IOException e) {
            kill();
        }
    }

    public void send(Serializable object) {
        try {
            out.writeObject(object);
        } catch (Exception e) {
            logger.error("Failed to send object", e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Connection) obj;
        return this.socket.equals(that.socket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socket);
    }

    /**
     * Removes the connection from its player instance and closes the socket.
     * This allows users to reconnect to a player instance with a new connection.
     */
    private void kill() {
        game.getPlayers().stream().filter(p -> p.connection().map(c -> c.equals(this)).getOrElse(false)).forEach(p -> p.setConnection(Option.none()));
        try {
            socket.close();
            logger.info("Connection closed");
        } catch (IOException e) {
            logger.error("Failed to close socket", e);
        }
    }
}
