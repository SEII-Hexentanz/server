package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.models.Response;
import at.aau.payloads.EmptyPayload;
import at.aau.payloads.RegisterPayload;
import at.aau.values.Color;
import at.aau.values.GameState;
import at.aau.values.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

// fixme classname and filename dont match
class RegisterCommandTest {
    private RegisterCommand registerCommand;
    private Game game;
    private Player player;
    private TreeSet<Player> players;

    @BeforeEach
    void setUp() {
        registerCommand = new RegisterCommand();
        game = mock(Game.class);
        player = mock(Player.class);
        players = new TreeSet<>();
        when(game.getPlayers()).thenReturn(players);
    }

    @Test
    void shouldSendGameFullWhenPlayerLimitReached() {
        when(game.getState()).thenReturn(GameState.LOBBY);
        for (int i = 0; i < 7; i++) {
            players.add(mock(Player.class));
        }

        registerCommand.execute(game, player, new RegisterPayload("name", 18));

        verify(player).send(new Response(ResponseType.GAME_FULL));
    }

    @Test
    void shouldSendGameFullWhenGameNotInLobbyState() {
        when(game.getState()).thenReturn(GameState.RUNNING);

        registerCommand.execute(game, player, new RegisterPayload("name", 18));

        verify(player).send(new Response(ResponseType.GAME_FULL));
    }

    @Test
    void shouldAddPlayerWhenGameInLobbyAndPlayerLimitNotReached() {
        when(game.getState()).thenReturn(GameState.LOBBY);
        for (int i = 0; i < 5; i++) {
            Player mock = mock(Player.class);
            when(mock.name()).thenReturn(String.valueOf(i));
            players.add(mock);
        }

        registerCommand.execute(game, player, new RegisterPayload("name", 18));
        assertTrue(players.contains(player));
    }

    @Test
    void shouldAssignUniqueColorToPlayer() {
        when(game.getState()).thenReturn(GameState.LOBBY);
        for (int i = 0; i < 5; i++) {
            Player mockPlayer = mock(Player.class);
            when(mockPlayer.color()).thenReturn(Color.values()[i]);
            when(mockPlayer.name()).thenReturn(String.valueOf(i));
            players.add(mockPlayer);
        }
        registerCommand.execute(game, player, new RegisterPayload("name", 18));
        assertTrue(players.contains(player));
        verify(player).setColor(Color.values()[5]);
    }

    @Test
    void shouldSendNameAlreadyExistsWhenPlayerNameAlreadyExists() {
        when(game.getState()).thenReturn(GameState.LOBBY);
        for (int i = 0; i < 5; i++) {
            Player mockPlayer = mock(Player.class);
            when(mockPlayer.name()).thenReturn(String.valueOf(i));
            players.add(mockPlayer);
        }
        registerCommand.execute(game, player, new RegisterPayload("1", 18));
        verify(player).send(new Response(ResponseType.NAME_ALREADY_EXISTS));
    }

    @Test
    void shouldSendBadRequestWhenPayloadIsNotRegisterPayload() {
        registerCommand.execute(game, player, new EmptyPayload());
        verify(player).send(new Response(ResponseType.BAD_REQUEST));
    }
}
