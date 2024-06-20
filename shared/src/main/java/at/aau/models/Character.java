package at.aau.models;

import at.aau.values.CharacterState;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public record Character(UUID id, int position, CharacterState status, int steps) implements Serializable {
    @Serial
    private static final long serialVersionUID = -8185411860134618456L;

    // Method to return a new Character with updated position
    public Character withPosition(int newPosition) {
        return new Character(this.id, newPosition, this.status, this.steps);
    }

    // Optional: Add similar methods for other fields if needed
    public Character withStatus(CharacterState newStatus) {
        return new Character(this.id, this.position, newStatus, this.steps);
    }

    public Character withSteps(int newSteps) {
        return new Character(this.id, this.position, this.status, newSteps);
    }
}
