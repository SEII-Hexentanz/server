package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.logic.GameEnd;
import at.aau.models.Response;
import at.aau.payloads.GameEndPayload;
import at.aau.payloads.Payload;
import at.aau.values.ResponseType;

public class MoveCommand implements Command {
    @Override
    public void execute(Game game, Player player, Payload payload) {
//        TODO: Implement PlayerMovement #37

        GameEnd.getWinner(game.toModel()).ifPresent(winner -> {
            game.broadcast(new Response(ResponseType.GAME_END, new GameEndPayload(winner)));
        });
    }
}
