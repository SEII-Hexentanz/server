package at.aau.models;

import at.aau.values.CharacterState;
import at.aau.values.Color;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Player(String name, int age, Color color,
                     List<Character> characters) implements Serializable, Comparable<Player> {
    @Serial
    private static final long serialVersionUID = -5185411860134618456L;
    static Sender sender;
    @Override
    public int compareTo(Player o) {
        return o.age() - this.age();
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, age, color, characters);
    }
    public void send(Serializable response) {
        if (sender != null) {
            sender.send(response);
        } else {
            throw new IllegalStateException("Sender not set");
        }
    }

    public static void setSender(Sender sender) {
        Player.sender = sender;
    }

    public Player updateCharacterState(int characterIndex, CharacterState newState) {
        Character updatedCharacter = new Character(characters().get(characterIndex).position(), newState);
        List<Character> updatedCharacters = new ArrayList<>(characters());
        updatedCharacters.set(characterIndex, updatedCharacter);
        return new Player(name, age, color, updatedCharacters);
    }

}
