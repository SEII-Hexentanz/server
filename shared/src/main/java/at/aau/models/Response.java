package at.aau.models;

import at.aau.payloads.EmptyPayload;
import at.aau.payloads.Payload;
import at.aau.values.ResponseType;

import java.io.Serial;
import java.io.Serializable;

public record Response(ResponseType responseType, Payload payload) implements Serializable {
    @Serial
    private static final long serialVersionUID = -926825434639594815L;
    public Response(ResponseType responseType) {
        this(responseType, new EmptyPayload());
    }
}
