package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.GameData;
import at.aau.models.Response;
import at.aau.payloads.DicePayload;
import at.aau.payloads.EmptyPayload;
import at.aau.payloads.Payload;
import at.aau.values.ResponseType;

public class DiceCommand  implements Command {
    @Override
    public void execute(Game game, Player player, Payload payload) {
        if (payload instanceof DicePayload diceRollPayload) {

            player.send(new Response(ResponseType.DICE_THROWN));
        } else {
            player.send(new Response(ResponseType.BAD_REQUEST, new EmptyPayload()));
        }
    }
}
