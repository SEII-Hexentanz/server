package at.aau.payloads;

import java.io.Serial;

import at.aau.models.Player;

public record DicePayload(int diceValue, Player player) implements Payload {

    @Serial
    private static final long serialVersionUID= 1921381893121L;
}
