package at.aau.commands;
import at.aau.Dice;
import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.Response;
import at.aau.payloads.EmptyPayload;
import at.aau.payloads.Payload;
import at.aau.values.ResponseType;

public class DiceRollCommand implements Command {
    @Override
    public void execute(Game game, Player player, Payload payload) {

        Dice dicePayload = (Dice) payload;
        int diceValue = dicePayload.getDiceNumber();
        if(isValidDiceValue(diceValue)){
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
