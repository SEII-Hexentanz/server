package at.aau.payloads;

import java.io.Serial;
import java.util.UUID;

public record PlayerMovePayload(UUID characterId, int newPosition) implements Payload {
    @Serial
    private static final long serialVersionUID = -1783123104231656319L;
}
