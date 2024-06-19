package at.aau.models;

import at.aau.values.Color;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record Player(String name, int age, Color color, boolean usedCheat,
                     List<Character> characters) implements Serializable, Comparable<Player> {
    @Serial
    private static final long serialVersionUID = -5185411860134618456L;

    @Override
    public int compareTo(Player o) {
        return o.age() - this.age();
    }
}
