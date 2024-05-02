package at.aau.payloads;

import java.io.Serial;

public record PlayerMovePayload(int newPosition) implements Payload {

    @Serial
    private static final long serialVersionUID= 19381893121L;
}
