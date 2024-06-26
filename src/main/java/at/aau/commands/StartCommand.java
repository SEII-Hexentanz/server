package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.Response;
import at.aau.payloads.Payload;
import at.aau.payloads.UpdateStatePayload;
import at.aau.payloads.YourTurnPayload;
import at.aau.values.GameState;
import at.aau.values.ResponseType;
import org.slf4j.LoggerFactory;

public class StartCommand implements Command {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(StartCommand.class);

    @Override
    public void execute(Game game, Player player, Payload payload) {
        if (game.getPlayers().size() >= 3 && game.getPlayers().size() <= 6) {
            game.setState(GameState.RUNNING);
            game.broadcast(new Response(ResponseType.UPDATE_STATE, new UpdateStatePayload(game.toModel())));
            //first player gets messaged that it is their turn

            Player p =  (Player) game.getPlayers().toArray()[0];

            p.send(new Response(ResponseType.YOUR_TURN, new YourTurnPayload(p.toModel())));
            System.out.println(p.name());
            logger.info("Send turn to next player: {}", p.name());
        } else {
            player.send(new Response(ResponseType.BAD_REQUEST));
        }
    }
}
