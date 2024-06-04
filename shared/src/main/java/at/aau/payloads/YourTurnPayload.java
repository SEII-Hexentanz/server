package at.aau.payloads;

import java.io.Serial;

import at.aau.models.Player;

public record YourTurnPayload(Player player) implements Payload {

    @Serial
    private static final long serialVersionUID= 19212132381893121L;
}