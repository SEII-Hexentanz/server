package at.aau.commands;
import org.slf4j.LoggerFactory;

import at.aau.Connection;
import at.aau.Dice;
import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.Response;
import at.aau.payloads.DicePayload;
import at.aau.payloads.EmptyPayload;
import at.aau.payloads.Payload;
import at.aau.values.ResponseType;

public class DiceRollCommand implements Command {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(DiceRollCommand.class);

    @Override
    public void execute(Game game, Player player, Payload payload) {
        Dice dice  = new Dice();
        int diceValue = dice.useDice();
        DicePayload dicePayload = new DicePayload(diceValue);


        if(isValidDiceValue(diceValue)){

            logger.info("INFO: DICE_ROLLED ", diceValue);
            System.out.println("DiceValue: " + diceValue);
            game.broadcast(new Response(ResponseType.DICE_ROLLED, dicePayload));

        }else{
            game.broadcast(new Response(ResponseType.BAD_REQUEST,new EmptyPayload()));
        }

    }

    private boolean isValidDiceValue(int diceValue){
        if(diceValue < 1 || diceValue >6){
            return false;
        }
        return true;
    }
}
