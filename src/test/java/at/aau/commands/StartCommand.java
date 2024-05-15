package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.commandHandler.Command;
import at.aau.models.Response;
import at.aau.values.GameState;
import at.aau.values.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.mockito.Mockito.*;

class StartCommandTest {
    private Game game;
    private Player player;
    private Command startCommand;
    private SortedSet<Player> players;

    @BeforeEach
    public void setup() {
        game = mock(Game.class);
        player = mock(Player.class);
        startCommand = new StartCommand();
        players = new TreeSet<>();
        when(game.getPlayers()).thenReturn(players);
    }

    @Test
    public void shouldStartGameWhenPlayerCountIsWithinRange() {
        for (int i = 0; i < 3; i++) {
            players.add(Mockito.mock(Player.class));
        }

        startCommand.execute(game, player, null);

        verify(game, times(1)).setState(GameState.RUNNING);
        verify(game, times(1)).broadcast(any(Response.class));
    }

    @Test
    public void shouldNotStartGameWhenPlayerCountIsLessThanThree() {
        for (int i = 0; i < 2; i++) {
            players.add(Mockito.mock(Player.class));
        }

        startCommand.execute(game, player, null);

        verify(game, times(0)).setState(GameState.RUNNING);
        verify(player, times(1)).send(new Response(ResponseType.BAD_REQUEST));
    }

    @Test
    public void shouldNotStartGameWhenPlayerCountIsMoreThanSix() {
        for (int i = 0; i < 7; i++) {
            players.add(Mockito.mock(Player.class));
        }

        startCommand.execute(game, player, null);

        verify(game, times(0)).setState(GameState.RUNNING);
        verify(player, times(1)).send(new Response(ResponseType.BAD_REQUEST));
    }
}
