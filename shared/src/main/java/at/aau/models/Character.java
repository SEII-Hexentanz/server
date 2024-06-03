package at.aau.models;

import at.aau.values.CharacterState;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public record Character(UUID id, int position, CharacterState status) implements Serializable {
    @Serial
    private static final long serialVersionUID = -8185411860134618456L;
}
