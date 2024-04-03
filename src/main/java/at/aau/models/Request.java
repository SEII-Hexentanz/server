package at.aau.models;

import at.aau.commandHandler.CommandType;
import at.aau.payloads.Payload;

import java.io.Serializable;

public record Request(CommandType commandType, Payload payload) implements Serializable {
}
