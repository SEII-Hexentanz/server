package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.GameData;
import at.aau.models.Response;
import at.aau.payloads.EmptyPayload;
import at.aau.payloads.Payload;
import at.aau.values.ResponseType;
import at.aau.payloads.TimerPayload;


public class TimerCommand implements Command {
    public void execute(Game game, Player player, Payload payload) {
        if (payload instanceof TimerPayload timerPayload) {
            // Handle timer logic
            //int remainingTime = timerPayload.remainingTime();
            //boolean isRunning = timerPayload.isRunning();

            GameData gameData = game.toModel();
            player.send(new Response(ResponseType.TIMER_RUNNING));
        } else {
            player.send(new Response(ResponseType.BAD_REQUEST, new EmptyPayload()));
        }
    }
}
