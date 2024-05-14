package at.aau.payloads;

import java.io.Serial;

public record DicePayload(int diceValue) implements Payload{
    @Serial
    private static final long serialVersionUID = 1383217793982015942L;
}
