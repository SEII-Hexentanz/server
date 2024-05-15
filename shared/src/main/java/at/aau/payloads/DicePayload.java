package at.aau.payloads;

import at.aau.models.Player;

import java.io.Serial;

public record DicePayload(int diceValue, Player player) implements Payload {
    @Serial
    private static final long serialVersionUID = 1383217793982015942L;
}
