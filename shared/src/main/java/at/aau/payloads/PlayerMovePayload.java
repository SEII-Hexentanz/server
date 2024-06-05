package at.aau.payloads;

import java.io.Serial;
import java.util.UUID;

import at.aau.values.MoveType;

public record PlayerMovePayload(UUID characterId, int newPosition, MoveType moveType) implements Payload {
    @Serial
    private static final long serialVersionUID = -1783123104231656319L;
}
