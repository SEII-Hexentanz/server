package at.aau.payloads;

import java.io.Serial;

public record PlayerMovePayload(int oldPosition,int newPosition) implements Payload {
    @Serial
    private static final long serialVersionUID= -1783123104231656319L;
}
