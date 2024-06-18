package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.logic.Dice;
import at.aau.models.Response;
import at.aau.payloads.DicePayload;
import at.aau.values.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DiceRollCommandTest {
    private Game game;
    private Player player1;
    private Player player2;
    private DiceRollCommand diceRollCommand;
    private Dice dice;

    @BeforeEach
    public void setup() {
        game = Mockito.mock(Game.class);
        player1 = Mockito.mock(Player.class);
        player2 = Mockito.mock(Player.class);
        dice = Mockito.mock(Dice.class);
        diceRollCommand = new DiceRollCommand(dice);
    }

    @Test
    public void invalidDiceValueBelow() {
        when(dice.useDice()).thenReturn(0);
        diceRollCommand.execute(game, player1, null);

        ArgumentCaptor<Response> argument = ArgumentCaptor.forClass(Response.class);
        verify(game).broadcast(argument.capture());
        assertEquals(ResponseType.DICE_ROLLED, argument.getValue().responseType());
        assertEquals(1, ((DicePayload) argument.getValue().payload()).diceValue());
    }

    @Test
    public void invalidDiceValueAbove() {
        when(dice.useDice()).thenReturn(7);
        diceRollCommand.execute(game, player1, null);

        ArgumentCaptor<Response> argument = ArgumentCaptor.forClass(Response.class);
        verify(game).broadcast(argument.capture());
        assertEquals(ResponseType.DICE_ROLLED, argument.getValue().responseType());
        assertEquals(1, ((DicePayload) argument.getValue().payload()).diceValue());
    }

    @Test
    public void validDiceBelow6AllInHome() {
        when(player1.allCharactersInHome()).thenReturn(true);
        when(dice.useDice()).thenReturn(4);
        when(game.activePlayerIndex()).thenReturn(1);
        TreeSet<Player> players = new TreeSet<>(Set.of(player1, player2));
        when(game.getPlayers()).thenReturn(players);
        diceRollCommand.execute(game, player1, null);

        ArgumentCaptor<Response> argument = ArgumentCaptor.forClass(Response.class);
        verify(game).broadcast(argument.capture());
        assertEquals(ResponseType.DICE_ROLLED, argument.getValue().responseType());
        assertEquals(4, ((DicePayload) argument.getValue().payload()).diceValue());

        ArgumentCaptor<Response> argument2 = ArgumentCaptor.forClass(Response.class);
        verify(player2).send(argument2.capture());
        assertEquals(ResponseType.YOUR_TURN, argument2.getValue().responseType());
    }

    @Test
    public void validDice6NotAllInHome() {
        when(player1.allCharactersInHome()).thenReturn(false);
        when(dice.useDice()).thenReturn(6);
        diceRollCommand.execute(game, player1, null);

        ArgumentCaptor<Response> argument = ArgumentCaptor.forClass(Response.class);
        verify(game).broadcast(argument.capture());
        assertEquals(ResponseType.DICE_ROLLED, argument.getValue().responseType());
        assertEquals(6, ((DicePayload) argument.getValue().payload()).diceValue());
        verifyNoMoreInteractions(game);
    }

    @Test
    public void validDice6AllInHome() {
        when(player1.allCharactersInHome()).thenReturn(true);
        when(dice.useDice()).thenReturn(6);
        diceRollCommand.execute(game, player1, null);

        ArgumentCaptor<Response> argument = ArgumentCaptor.forClass(Response.class);
        verify(game).broadcast(argument.capture());
        assertEquals(ResponseType.DICE_ROLLED, argument.getValue().responseType());
        assertEquals(6, ((DicePayload) argument.getValue().payload()).diceValue());
        verifyNoMoreInteractions(game);
    }
}