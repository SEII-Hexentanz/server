package at.aau.payloads;

import java.io.Serial;

public record PlayerMovePayload(int newPosition) implements Payload {

    private static final long serialVersionUID= -1783123104231656319L;
}
