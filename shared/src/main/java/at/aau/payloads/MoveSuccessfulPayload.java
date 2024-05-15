package at.aau.payloads;

import java.io.Serial;

public record MoveSuccessfulPayload(String playerName, int characterIndex, int oldPosition, int newPosition) implements Payload {
    @Serial
    private static final long serialVersionUID= 3450535204952368681L;
}
