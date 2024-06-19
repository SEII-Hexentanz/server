package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.models.Response;
import at.aau.payloads.EmptyPayload;
import at.aau.values.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class PingCommandTest {
    private Game game;
    private Player player;
    private PingCommand pingCommand;

    @BeforeEach
    public void setup() {
        game = Mockito.mock(Game.class);
        player = Mockito.mock(Player.class);
        pingCommand = new PingCommand();
    }

    @Test
    public void pingCommandSendsPongResponse() {
        pingCommand.execute(game, player, new EmptyPayload());

        verify(player).send(new Response(ResponseType.PONG, new EmptyPayload()));
    }
}