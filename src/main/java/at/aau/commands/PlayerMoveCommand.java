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
            int oldPosition = movePayload.oldPosition();
            int newPosition = movePayload.newPosition();
            String playerName = movePayload.playerName();

            // Update the player's position in the game
            player.setPosition(newPosition);

            // Notify the player about the successful move
            player.send(new Response(ResponseType.UPDATE_STATE, new PlayerMovePayload(oldPosition, newPosition,playerName)));
            //player.send(new Response(ResponseType.MOVE_SUCCESSFUL, new PlayerMovePayload(oldPosition, newPosition,playerName)));
        } else {
            player.send(new Response(ResponseType.BAD_REQUEST, new EmptyPayload()));
        }
    }
}
