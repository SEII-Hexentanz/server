package at.aau;

import at.aau.models.Player;
import at.aau.models.Response;
import at.aau.values.GameState;

import java.io.Serializable;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class Game implements Serializable {
    private final SortedSet<Player> players = Collections.synchronizedSortedSet(new TreeSet<>());
    private GameState state = GameState.LOBBY;

    public SortedSet<Player> getPlayers() {
        return players;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void broadcast(Response response) {
        players.forEach(p -> p.send(response));
    }
}
