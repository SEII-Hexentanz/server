package at.aau.payloads;

import java.io.Serial;

public record EmptyPayload() implements Payload {
    @Serial
    private static final long serialVersionUID = 1383197793982015942L;
}
