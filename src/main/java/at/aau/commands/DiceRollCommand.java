package at.aau.commands;

import at.aau.Dice;
import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.Response;
import at.aau.payloads.DicePayload;
import at.aau.payloads.Payload;
import at.aau.values.ResponseType;
import org.slf4j.LoggerFactory;

public class DiceRollCommand implements Command {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(DiceRollCommand.class);

    @Override
    public void execute(Game game, Player player, Payload payload) {
        Dice dice = new Dice();
        int diceValue = dice.useDice();
        DicePayload dicePayload = new DicePayload(diceValue, player.toModel());

        if (isValidDiceValue(diceValue)) {
            logger.info("Dice rolled: {}", diceValue);
            game.broadcast(new Response(ResponseType.DICE_ROLLED, dicePayload));
        } else {
            logger.info("Invalid dice value: {}", diceValue);
            game.broadcast(new Response(ResponseType.DICE_ROLLED, new DicePayload(1, player.toModel())));
        }
    }

    private boolean isValidDiceValue(int diceValue) {
        return diceValue >= 1 && diceValue <= 6;
    }
}
