package at.aau.payloads;

import java.io.Serial;

import at.aau.models.Player;

public record CheatPayload(boolean cheatUsed,Player player) implements Payload {
    @Serial
    private static final long serialVersionUID = -8785341006961801199L;
}
