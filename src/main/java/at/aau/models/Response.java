package at.aau.models;

import at.aau.payloads.Payload;
import at.aau.values.ResponseType;

import java.io.Serializable;

public record Response(ResponseType responseType, Payload payload) implements Serializable {
}
