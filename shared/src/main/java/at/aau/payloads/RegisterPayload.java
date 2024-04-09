package at.aau.payloads;

import java.io.Serial;

public record RegisterPayload(String name, int age) implements Payload {
    @Serial
    private static final long serialVersionUID = 6330259930853934550L;
}
