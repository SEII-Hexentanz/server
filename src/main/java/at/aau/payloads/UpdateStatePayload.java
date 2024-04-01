package at.aau.payloads;

import at.aau.Game;

public record UpdateStatePayload(Game game) implements Payload {
}
