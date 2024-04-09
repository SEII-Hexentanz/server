package at.aau.payloads;

import at.aau.models.GameData;

import java.io.Serial;

public record UpdateStatePayload(GameData game) implements Payload {
    @Serial
    private static final long serialVersionUID = -561968222609796600L;
}
