package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.logic.GameEnd;
import at.aau.models.Character;
import at.aau.models.Response;
import at.aau.payloads.GameEndPayload;
import at.aau.payloads.Payload;
import at.aau.payloads.PlayerMovePayload;
import at.aau.payloads.YourTurnPayload;
import at.aau.values.CharacterState;
import at.aau.values.ResponseType;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MoveCommand implements Command {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(DiceRollCommand.class);

    @Override
    public void execute(Game game, Player player, Payload payload) {
        if (payload instanceof PlayerMovePayload movePayload) {

            if (player == game.getPlayers().toArray()[game.activePlayerIndex()]) {
                player.characters().stream()
                        .filter(c -> c.id().equals(movePayload.characterId()))
                        .findFirst()
                        .ifPresentOrElse(character -> {
                            if (character.status() != CharacterState.GOAL) {
                                player.setCharacters(player.characters().stream()
                                        .map(c -> c.id().equals(movePayload.characterId())
                                                //if needed send steps from client and update it here
                                                ? new Character(c.id(), movePayload.newPosition(), c.status(), movePayload.steps())
                                                : c)
                                        .collect(Collectors.toCollection(ArrayList::new)));

                                //player.send(new Response(ResponseType.MOVE_SUCCESSFUL));

                                game.broadcast(new Response(ResponseType.MOVE_SUCCESSFUL, new PlayerMovePayload(movePayload.characterId(), movePayload.newPosition(), movePayload.moveType(), movePayload.steps())));
                                logger.info("MOVE_CHARACTER {} to position {} .", movePayload.characterId(), movePayload.newPosition());


                                game.setActivePlayerIndex((game.activePlayerIndex() + 1) % game.getPlayers().size());

                                GameEnd.getWinner(game.toModel()).ifPresent(winner ->
                                        game.broadcast(new Response(ResponseType.GAME_END, new GameEndPayload(winner))));

                                Player nextPlayer = (Player) game.getPlayers().toArray()[game.activePlayerIndex()];
                                nextPlayer.send(new Response(ResponseType.YOUR_TURN, new YourTurnPayload(nextPlayer.toModel())));
                            } else {
                                logger.info("Player {} tried to move a character that is already in the goal.", player.name());
                                player.send(new Response(ResponseType.BAD_REQUEST));
                            }
                        }, () -> {
                            logger.info("Player {} tried to move a character that does not exist.", player.name());
                            player.send(new Response(ResponseType.BAD_REQUEST));
                        });

            } else {
                logger.info("Player {} tried to move a character without being the active player.", player.name());
                player.send(new Response(ResponseType.BAD_REQUEST));
            }
        }
    }
}
