package at.aau.commands;

import org.slf4j.LoggerFactory;

import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.Response;
import at.aau.payloads.CheatPayload;
import at.aau.payloads.Payload;
import at.aau.values.ResponseType;

public class CheatCommand implements Command {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CheatCommand.class);

    @Override
    public void execute(Game game, Player player, Payload payload) {
        boolean cheatValue= player.usedCheat();
        if (cheatValue) {
            logger.info("Player"+ player.name()+" attempted to use cheat but has already used it" );
            player.send(new Response(ResponseType.CHEAT_USED_PLAYER, null));
            return;
        }

        CheatPayload cheatPayload = new CheatPayload(true,player.toModel());

        // Implement the cheat action
        logger.info("Player " +player.name()+" used cheat");
        player.setUsedCheat(true);
        game.broadcast(new Response(ResponseType.CHEAT_USED_PLAYER, cheatPayload));
    }


}
