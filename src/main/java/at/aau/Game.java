package at.aau;

import at.aau.models.GameData;
import at.aau.models.Response;
import at.aau.values.GameState;

import java.io.Serializable;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Game implements Serializable {
    private final SortedSet<Player> players = Collections.synchronizedSortedSet(new TreeSet<>());
    private GameState state = GameState.LOBBY;

    private int currentPlayerIndex = 0;

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

    public GameData toModel() {
        return new GameData(players.stream().map(Player::toModel).collect(Collectors.toCollection(TreeSet::new)), state);
    }

    public Player getNextPlayer(){
        if(currentPlayerIndex >= players.size()){
            currentPlayerIndex = 0;
        }

        return (Player) players.toArray()[currentPlayerIndex++];
    }
}
