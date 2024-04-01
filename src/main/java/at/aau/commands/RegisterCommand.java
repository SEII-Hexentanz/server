package at.aau.commands;

import at.aau.Game;
import at.aau.commandHandler.Command;
import at.aau.models.Player;
import at.aau.models.Response;
import at.aau.payloads.EmptyPayload;
import at.aau.payloads.Payload;
import at.aau.values.GameState;
import at.aau.values.ResponseType;

public class RegisterCommand implements Command {
    @Override
    public void execute(Game game, Player player, Payload payload) {
        if (game.players.size() <= 6 || game.state != GameState.LOBBY) {
            player.send(new Response(ResponseType.GAME_FULL, new EmptyPayload()));
        } else {
            player.color = game.players.stream().map(Player::color).reduce((c1, c2) -> c1 == c2 ? c1 : null).orElse(null);
            game.players.add(player);
        }
    }
}
