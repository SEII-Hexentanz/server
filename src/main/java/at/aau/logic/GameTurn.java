package at.aau.logic;

import org.slf4j.LoggerFactory;

import java.util.SortedSet;

import at.aau.Player;
import at.aau.commands.DiceRollCommand;
import at.aau.models.Response;
import at.aau.payloads.EmptyPayload;
import at.aau.values.CommandType;
import at.aau.values.ResponseType;

public class GameTurn {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(DiceRollCommand.class);
    private int turnCounter;
    private int playerIndex = 0;

    private void endTurn(){
        logger.info("GAMEINFO: Current turn finished");
    }

    private void startTurn(SortedSet<Player> players){
        logger.info("GAMEINFO: Starting new turn");
        //turnCounter++;
        //playerIndex = (turnCounter+3);

        for(Player player:players){
            player.send(new Response(ResponseType.YOUR_TURN, new EmptyPayload()));
            //wait for player to finish his round
        }
    }
}