package at.aau.models;

import at.aau.values.CharacterState;

import java.io.Serial;
import java.io.Serializable;

public record Character(int position, CharacterState status) implements Serializable {
    @Serial
    private static final long serialVersionUID = -8185411860134618456L;
}
