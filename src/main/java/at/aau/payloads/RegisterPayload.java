package at.aau.payloads;

public record RegisterPayload(String name, int age) implements Payload {
}
