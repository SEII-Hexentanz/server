package at.aau;

import at.aau.models.Player;
import at.aau.models.Response;
import at.aau.values.GameState;

import java.io.Serializable;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class Game implements Serializable {
    public SortedSet<Player> players = Collections.synchronizedSortedSet(new TreeSet<>());

    public GameState state = GameState.LOBBY;

    public void broadcast(Response response) {
        players.forEach(p -> p.send(response));
    }
}
