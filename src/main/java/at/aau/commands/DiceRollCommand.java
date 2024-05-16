package at.aau.commands;
import org.slf4j.LoggerFactory;

import at.aau.Dice;
import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.Response;
import at.aau.payloads.DicePayload;
import at.aau.payloads.EmptyPayload;
import at.aau.payloads.Payload;
import at.aau.payloads.YourTurnPayload;
import at.aau.values.ResponseType;

public class DiceRollCommand implements Command {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(DiceRollCommand.class);

    @Override
    public void execute(Game game, Player player, Payload payload) {
        Dice dice  = new Dice();
        int diceValue = dice.useDice();
        DicePayload dicePayload = new DicePayload(diceValue, player.toModel());

        if (isValidDiceValue(diceValue)) {
            logger.info("Dice rolled: {}", diceValue);
            game.broadcast(new Response(ResponseType.DICE_ROLLED, dicePayload));
            //next player gets messaged that is their turn; relocate to move
            Player nextPlayer = game.getNextPlayer();
            nextPlayer.send(new Response(ResponseType.YOUR_TURN, new YourTurnPayload()));
            logger.info("Send turn to next player: ", nextPlayer.name());
        } else {
            logger.info("Invalid dice value: {}", diceValue);
            game.broadcast(new Response(ResponseType.DICE_ROLLED, new DicePayload(1, player.toModel())));
        }
    }

    private boolean isValidDiceValue(int diceValue) {
        return diceValue >= 1 && diceValue <= 6;
    }
}
