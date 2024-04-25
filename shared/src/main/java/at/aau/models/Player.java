package at.aau.models;

import at.aau.values.Color;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public record Player(String name, int age, Color color, Character[] characters) implements Serializable, Comparable<Player> {
    @Serial
    private static final long serialVersionUID = -5185411860134618456L;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Player player = (Player) obj;
        return age == player.age && Objects.equals(name, player.name) && color == player.color && Arrays.equals(characters, player.characters);
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(name, age, color);
        result = 31 * result + Arrays.hashCode(characters);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color=" + color +
                ", characters=" + Arrays.toString(characters) +
                '}';
    }
    @Override
    public int compareTo(Player o) {
        return o.age() - this.age();
    }
}
