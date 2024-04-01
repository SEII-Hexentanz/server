package at.aau;

import at.aau.commandHandler.CommandHandler;
import at.aau.commandHandler.CommandType;
import at.aau.models.Player;
import at.aau.models.Request;
import at.aau.payloads.RegisterPayload;
import at.aau.util.SecureObjectInputStream;
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
    private final Game game;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(Connection.class);

    public Connection(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;
    }

    @Override
    public void run() {
        try (
                var in = new SecureObjectInputStream(socket.getInputStream());
                var out = new ObjectOutputStream(socket.getOutputStream())
        ) {
            while (true) {
                Request request;
                try {
                    request = (Request) in.readObject();
                } catch (ClassNotFoundException e) {
                    logger.error("Failed to read object", e);
                    send(ResponseType.BAD_REQUEST);
                    continue;
                }
                if (request.commandType() == CommandType.REGISTER && request.payload() instanceof RegisterPayload payload) {
                    var player = new Player(this, payload.name(), payload.age());
                    CommandHandler.execute(request, player, game);
                    continue;
                }
                game.players.stream().filter(player -> Objects.equals(player.connection.getOrNull(), this))
                        .findFirst().ifPresentOrElse(player -> CommandHandler.execute(request, player, game),
                                () -> send(ResponseType.NOT_REGISTERED));
            }
        } catch (IOException e) {
            kill();
        }
    }

    public void send(Serializable object) {
        try (var out = new ObjectOutputStream(socket.getOutputStream())) {
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
        game.players.stream().filter(p -> p.connection.map(c -> c.equals(this)).getOrElse(false)).forEach(p -> p.connection = Option.none());
        try {
            socket.close();
        } catch (IOException e) {
            logger.error("Failed to close socket", e);
        }
    }
}
