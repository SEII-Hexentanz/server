package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.Response;
import at.aau.payloads.EmptyPayload;
import at.aau.payloads.Payload;
import at.aau.payloads.PlayerMovePayload;
import at.aau.values.ResponseType;

public class PlayerMoveCommand implements Command {
    @Override
    public void execute(Game game, Player player, Payload payload) {
        if (payload instanceof PlayerMovePayload movePayload) {
            // Update the player's position
            player.setPosition(movePayload.newPosition());
            // Notify the player about the successful move
            player.send(new Response(ResponseType.MOVE_SUCCESSFUL, new PlayerMovePayload(movePayload.newPosition())));
        } else {
            player.send(new Response(ResponseType.BAD_REQUEST, new EmptyPayload()));
        }
    }
}
