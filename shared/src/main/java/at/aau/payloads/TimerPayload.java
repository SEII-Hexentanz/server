package at.aau.payloads;

import java.io.Serial;

public record TimerPayload() implements Payload{
    @Serial
    private static final long serialVersionUID= 8828366451294774125L;
}
