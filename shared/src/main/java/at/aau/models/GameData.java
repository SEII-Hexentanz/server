package at.aau.models;

import at.aau.values.GameState;

import java.io.Serial;
import java.io.Serializable;
import java.util.SortedSet;

public record GameData(SortedSet<Player> players, GameState gameState) implements Serializable {
    @Serial
    private static final long serialVersionUID = 6820338999087102987L;
}
