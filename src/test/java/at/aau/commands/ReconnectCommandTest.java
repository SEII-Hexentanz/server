package at.aau.commands;

import at.aau.Connection;
import at.aau.Game;
import at.aau.Player;
import at.aau.models.Response;
import at.aau.payloads.EmptyPayload;
import at.aau.payloads.RegisterPayload;
import at.aau.values.ResponseType;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReconnectCommandTest {
    private Game game;
    private Player player;
    private ReconnectCommand reconnectCommand;
    private RegisterPayload registerPayload;

    @BeforeEach
    public void setup() {
        game = Mockito.mock(Game.class);
        player = Mockito.mock(Player.class);
        reconnectCommand = new ReconnectCommand();
        registerPayload = new RegisterPayload("name", 20);
    }

    @Test
    public void BadRequestIfConnectionDefined() {
        var mockConn = Mockito.mock(Connection.class);
        when(player.connection()).thenReturn(Option.of(mockConn));
        var players = new TreeSet<>(Set.of(player));
        when(game.getPlayers()).thenReturn(players);
        when(player.name()).thenReturn("name");
        reconnectCommand.execute(game, player, registerPayload);

        ArgumentCaptor<Response> argument = ArgumentCaptor.forClass(Response.class);
        verify(player).send(argument.capture());
        assertEquals(ResponseType.BAD_REQUEST, argument.getValue().responseType());
    }

    @Test
    public void PongIfPlayerPresentAndNoConnection() {
        when(player.connection()).thenReturn(Option.none());
        var players = new TreeSet<>(Set.of(player));
        when(game.getPlayers()).thenReturn(players);
        when(player.name()).thenReturn("name");
        reconnectCommand.execute(game, player, registerPayload);

        verify(player).setConnection(any());
        ArgumentCaptor<Response> argument = ArgumentCaptor.forClass(Response.class);
        verify(player).send(argument.capture());
        assertEquals(ResponseType.PONG, argument.getValue().responseType());
    }

    @Test
    public void NotRegisteredIfPlayerNotPresent() {
        reconnectCommand.execute(game, player, registerPayload);

        ArgumentCaptor<Response> argument = ArgumentCaptor.forClass(Response.class);
        verify(player).send(argument.capture());
        assertEquals(ResponseType.NOT_REGISTERED, argument.getValue().responseType());
    }

    @Test
    public void NothingIfWrongPayload() {
        reconnectCommand.execute(game, player, new EmptyPayload());

        verifyNoInteractions(player, game);
    }
}
