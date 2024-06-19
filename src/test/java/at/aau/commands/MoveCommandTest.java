package at.aau.commands;

import at.aau.Game;
import at.aau.Player;
import at.aau.models.Character;
import at.aau.models.GameData;
import at.aau.payloads.PlayerMovePayload;
import at.aau.values.CharacterState;
import at.aau.values.GameState;
import at.aau.values.MoveType;
import at.aau.values.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Mockito.*;

class MoveCommandTest {
    private Game game;
    private Player player;
    private MoveCommand moveCommand;
    private PlayerMovePayload playerMovePayload;
    private UUID characterId;

    @BeforeEach
    public void setup() {
        game = Mockito.mock(Game.class);
        player = Mockito.mock(Player.class);
        moveCommand = new MoveCommand();
        characterId = UUID.randomUUID();
        playerMovePayload = new PlayerMovePayload(characterId, 0, MoveType.MOVE_ON_FIELD, 0);
    }

    @Test
    public void PlayerNotActive() {
        when(game.activePlayerIndex()).thenReturn(0);
        var player2 = Mockito.mock(Player.class);
        var players = new TreeSet<>(Set.of(player2));
        when(game.getPlayers()).thenReturn(players);
        moveCommand.execute(game, player, playerMovePayload);

        verify(player).send(Mockito.argThat(response -> response.responseType() == ResponseType.BAD_REQUEST));
        verify(player, never()).characters();
    }

    @Test
    public void CharacterDoesntExist() {
        when(game.activePlayerIndex()).thenReturn(0);
        var players = new TreeSet<>(Set.of(player));
        var character = Mockito.mock(Character.class);
        when(character.id()).thenReturn(UUID.randomUUID());
        when(player.characters()).thenReturn(new ArrayList<>(List.of(character)));
        when(game.getPlayers()).thenReturn(players);
        when(player.characters()).thenReturn(new ArrayList<>());
        moveCommand.execute(game, player, playerMovePayload);

        verify(player).send(Mockito.argThat(response -> response.responseType() == ResponseType.BAD_REQUEST));
        verify(character, never()).status();
    }

    @Test
    public void CharacterInGoal() {
        when(game.activePlayerIndex()).thenReturn(0);
        var players = new TreeSet<>(Set.of(player));
        var character = Mockito.mock(Character.class);
        when(character.id()).thenReturn(characterId);
        when(character.status()).thenReturn(CharacterState.GOAL);
        when(player.characters()).thenReturn(new ArrayList<>(List.of(character)));
        when(game.getPlayers()).thenReturn(players);
        moveCommand.execute(game, player, playerMovePayload);

        verify(player).send(Mockito.argThat(response -> response.responseType() == ResponseType.BAD_REQUEST));
        verify(character).status();
        verify(player, never()).setCharacters(Mockito.any());
    }

    @Test
    public void MoveCharacter() {
        when(game.activePlayerIndex()).thenReturn(0);
        var players = new TreeSet<>(Set.of(player));
        var character = Mockito.mock(Character.class);
        when(character.id()).thenReturn(characterId);
        when(character.status()).thenReturn(CharacterState.FIELD);
        when(player.characters()).thenReturn(new ArrayList<>(List.of(character)));
        when(game.getPlayers()).thenReturn(players);
        var gameData = new GameData(new TreeSet<>(), GameState.RUNNING, 0);
        when(game.toModel()).thenReturn(gameData);
        moveCommand.execute(game, player, playerMovePayload);

        verify(player).setCharacters(Mockito.argThat(characters -> characters.size() == 1 && characters.get(0).id().equals(characterId)));
        verify(game).broadcast(Mockito.argThat(response -> response.responseType() == ResponseType.MOVE_SUCCESSFUL));
    }

    @Test
    public void WrongPayload() {
        moveCommand.execute(game, player, null);

        verify(player, never()).send(Mockito.any());
        verify(game, never()).broadcast(Mockito.any());
    }
}
