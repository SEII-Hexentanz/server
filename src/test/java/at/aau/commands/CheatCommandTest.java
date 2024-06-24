package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.models.Character;
import at.aau.models.Response;
import at.aau.payloads.CheatPayload;
import at.aau.payloads.Payload;
import at.aau.values.CharacterState;
import at.aau.values.Color;
import at.aau.values.ResponseType;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CheatCommandTest {

    private Game game;
    private Player player;
    private CheatCommand cheatCommand;
    private Payload payload;

    @BeforeEach
    public void setup() {
        game = mock(Game.class);
        player = mock(Player.class); // Mock the Player object
        payload = mock(Payload.class);
        cheatCommand = new CheatCommand();
    }

    @Test
    public void testExecute_CheatAlreadyUsed() {
        when(player.usedCheat()).thenReturn(true);
        when(player.name()).thenReturn("TestPlayer");

        cheatCommand.execute(game, player, payload);

        verify(player).send(Mockito.argThat(response -> response.responseType() == ResponseType.CHEAT_USED_PLAYER));
        verify(game, never()).broadcast(any(Response.class));
    }

    @Test
    public void testExecute_CheatNotUsed() {
        when(player.usedCheat()).thenReturn(false);
        when(player.name()).thenReturn("TestPlayer");

        ArrayList<Character> characters = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            characters.add(new Character(UUID.randomUUID(), 0, CharacterState.HOME, 0));
        }

        when(player.toModel()).thenReturn(new at.aau.models.Player("TestPlayer", 20, Color.RED, false, characters));

        cheatCommand.execute(game, player, payload);

        verify(player).setUsedCheat(true);

        ArgumentCaptor<Response> responseCaptor = ArgumentCaptor.forClass(Response.class);
        verify(game).broadcast(responseCaptor.capture());
        Response response = responseCaptor.getValue();
        assertEquals(ResponseType.CHEAT_USED_PLAYER, response.responseType());
    }
}
