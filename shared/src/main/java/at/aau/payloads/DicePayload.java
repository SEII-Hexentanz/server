package at.aau.payloads;

import java.io.Serial;

public record DicePayload(int diceValue) implements Payload {

    @Serial
    private static final long serialVersionUID= 1921381893121L;
}
