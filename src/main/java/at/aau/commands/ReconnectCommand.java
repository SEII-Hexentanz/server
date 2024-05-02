package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.Response;
import at.aau.payloads.Payload;
import at.aau.payloads.RegisterPayload;
import at.aau.values.ResponseType;
import io.vavr.control.Option;

public class ReconnectCommand implements Command {
    @Override
    public void execute(Game game, Player player, Payload payload) {
        if (payload instanceof RegisterPayload registerPayload) {
            game.getPlayers().stream().filter(p -> p.name().equals(registerPayload.name())).findFirst()
                    .ifPresentOrElse(p -> {
                        if (p.connection.isDefined()) {
                            player.send(new Response(ResponseType.BAD_REQUEST));
                        } else {
                            p.connection = Option.some(player.connection.getOrNull());
                            player.send(new Response(ResponseType.PONG));
                        }
                    }, () -> player.send(new Response(ResponseType.NOT_REGISTERED)));
        }
    }
}
