package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.Response;
import at.aau.payloads.EmptyPayload;
import at.aau.payloads.Payload;
import at.aau.payloads.RegisterPayload;
import at.aau.values.Color;
import at.aau.values.GameState;
import at.aau.values.ResponseType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RegisterCommand implements Command {
    @Override
    public void execute(Game game, Player player, Payload payload) {
        if (payload instanceof RegisterPayload registerPayload) {
            if (game.getPlayers().size() >= 6 || game.getState() != GameState.LOBBY) {
                player.send(new Response(ResponseType.GAME_FULL));
            } else if (game.getPlayers().stream().anyMatch(p -> p.name().equals(registerPayload.name()))) {
                player.send(new Response(ResponseType.NAME_ALREADY_EXISTS, new EmptyPayload()));
            } else {
                var usedColors = game.getPlayers().stream().map(Player::color).collect(Collectors.toSet());
                var color = Arrays.stream(Color.values()).filter(c -> !usedColors.contains(c)).findFirst().orElseThrow();
                player.setColor(color);
                game.getPlayers().add(player);
                player.send(new Response(ResponseType.PLAYER_SUCCESSFULLY_REGISTERED, new EmptyPayload()));
            }
        } else player.send(new Response(ResponseType.BAD_REQUEST));
    }
}