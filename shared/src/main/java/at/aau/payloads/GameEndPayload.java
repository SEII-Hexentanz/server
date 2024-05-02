package at.aau.payloads;

import at.aau.models.Player;

import java.io.Serial;

public record GameEndPayload(Player winner) implements Payload {
    @Serial
    private static final long serialVersionUID = -4075219563610530250L;
}
