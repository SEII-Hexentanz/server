package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.Response;
import at.aau.payloads.Payload;
import at.aau.payloads.UpdateStatePayload;
import at.aau.values.GameState;
import at.aau.values.ResponseType;

public class StartCommand implements Command {
    @Override
    public void execute(Game game, Player player, Payload payload) {
        if (game.getPlayers().size() >= 1 && game.getPlayers().size() <= 6) {
            game.setState(GameState.RUNNING);
            game.broadcast(new Response(ResponseType.UPDATE_STATE, new UpdateStatePayload(game.toModel())));
        } else {
            player.send(new Response(ResponseType.BAD_REQUEST));
        }
    }
}
