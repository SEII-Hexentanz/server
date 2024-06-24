package at.aau.logic;

import at.aau.models.Character;
import at.aau.models.GameData;
import at.aau.models.Player;
import at.aau.values.CharacterState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class GameEndTest {
    private GameData gameData;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setup() {
        gameData = Mockito.mock(GameData.class);
        player1 = Mockito.mock(Player.class);
        player2 = Mockito.mock(Player.class);
    }

    @Test
    public void getWinner() {
        var players = new TreeSet<>(Set.of(player1, player2));
        var goalChar = Mockito.mock(Character.class);
        when(goalChar.status()).thenReturn(CharacterState.GOAL);
        var fieldChar = Mockito.mock(Character.class);
        when(fieldChar.status()).thenReturn(CharacterState.FIELD);
        var p1Chars = Arrays.asList(
                goalChar,
                goalChar,
                fieldChar,
                fieldChar
        );
        var p2Chars = Arrays.asList(
                goalChar,
                goalChar,
                goalChar,
                goalChar
        );
        when(player1.characters()).thenReturn(p1Chars);
        when(player2.characters()).thenReturn(p2Chars);
        when(gameData.players()).thenReturn(players);

        Optional<Player> winner = GameEnd.getWinner(gameData);

        assertEquals(player2, winner.get());
    }

    @Test
    public void getWinnerNoWinner() {
        var players = new TreeSet<>(Set.of(player1, player2));
        var goalChar = Mockito.mock(Character.class);
        when(goalChar.status()).thenReturn(CharacterState.GOAL);
        var fieldChar = Mockito.mock(Character.class);
        when(fieldChar.status()).thenReturn(CharacterState.FIELD);
        var p1Chars = Arrays.asList(
                goalChar,
                goalChar,
                fieldChar,
                fieldChar
        );
        var p2Chars = Arrays.asList(
                goalChar,
                goalChar,
                fieldChar,
                fieldChar
        );
        when(player1.characters()).thenReturn(p1Chars);
        when(player2.characters()).thenReturn(p2Chars);
        when(gameData.players()).thenReturn(players);

        Optional<Player> winner = GameEnd.getWinner(gameData);

        assertTrue(winner.isEmpty());
    }
}