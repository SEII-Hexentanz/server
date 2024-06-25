package at.aau.logic;

import at.aau.models.GameData;
import at.aau.models.Player;
import at.aau.values.CharacterState;

import java.util.Optional;

public class GameEnd {
    public static Optional<Player> getWinner(GameData gameData) {
        for (Player player : gameData.players()) {
            if (player.characters().stream().anyMatch(character -> character.status() == CharacterState.GOAL)) {
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }
}
