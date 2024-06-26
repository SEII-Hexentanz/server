package at.aau.commandHandler;

import at.aau.Game;
import at.aau.Player;
import at.aau.payloads.Payload;

@FunctionalInterface
public interface Command {
    void execute(Game game, Player player, Payload payload);
}
