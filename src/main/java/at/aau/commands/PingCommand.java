package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.Response;
import at.aau.payloads.EmptyPayload;
import at.aau.payloads.Payload;
import at.aau.values.ResponseType;

public class PingCommand implements Command {
    @Override
    public void execute(Game game, Player player, Payload payload) {
        player.send(new Response(ResponseType.PONG, new EmptyPayload()));
    }
}
